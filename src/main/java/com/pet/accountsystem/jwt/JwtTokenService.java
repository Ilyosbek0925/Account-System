package com.pet.accountsystem.jwt;

import com.pet.accountsystem.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class JwtTokenService {

    @Value("${jwt.secret.key}")
    private String secret;

    @Value("${jwt.access.expiry}")
    private long accessTokenExpiry;

    @Value("${jwt.refresh.expiry}")
    private long refreshTokenExpiry;

    private Key secretKey;

    @PostConstruct
    public void init() {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(UserDetails userEntity) {
        Date expiryDate = new Date(System.currentTimeMillis() + accessTokenExpiry);
        return Jwts.builder()
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .setSubject(userEntity.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .addClaims(Map.of("roles", getRoles(userEntity.getAuthorities())))
                .compact();
    }

    public String generateRefreshToken(UserDetails userEntity) {
        Date expiryDate = new Date(System.currentTimeMillis() + refreshTokenExpiry);
        return Jwts.builder()
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .setSubject(userEntity.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .addClaims(Map.of("roles",getRoles(userEntity.getAuthorities())))
                .compact();
    }

    public List<String> getRoles(Collection<? extends GrantedAuthority> roles) {
        return roles.stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
    }

    public Jws<Claims> extractToken(String token) {
        log.warn("at least chack");
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);
    }
}

