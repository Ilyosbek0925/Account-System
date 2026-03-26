package com.pet.accountsystem.service.auth;

import com.pet.accountsystem.exception.NotAcceptableException;
import com.pet.accountsystem.jwt.JwtTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtTokenService jwtService;

    public void authenticate(Claims claims, HttpServletRequest request) throws Exception {
        String username = claims.getSubject();

        @SuppressWarnings("unchecked")
        List<String> roles = claims.get("roles", List.class);
        log.warn("authenticate");
        if (roles == null) {
            throw new IllegalArgumentException("No roles found in JWT claims");
        }
        log.warn("credentials " + username + " role" + roles);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        getRoles(roles)
                );
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    public String authenticateRefreshToken(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String token = request.getHeader("authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            throw new NotAcceptableException("Refresh token is missing or invalid");
        }

        token = token.substring(7);
        Jws<Claims> claimsJws = jwtService.extractToken(token);
        Claims claims = claimsJws.getBody();

        Date expiration = claims.getExpiration();
        if (new Date().after(expiration)) {
            throw new NotAcceptableException("Expired refresh token!");
        }

        try {
            this.authenticate(claims, request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return claims.getSubject();
    }


    public List<SimpleGrantedAuthority> getRoles(List<String> roles) {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }
}
