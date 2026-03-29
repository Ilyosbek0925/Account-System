package com.pet.accountsystem.service.impl;

import com.pet.accountsystem.dto.LoginDto;
import com.pet.accountsystem.dto.request.RefreshRequest;
import com.pet.accountsystem.dto.response.LoginResponseDto;
import com.pet.accountsystem.entity.UserEntity;
import com.pet.accountsystem.exception.DataNotFoundException;
import com.pet.accountsystem.exception.NotAcceptableException;
import com.pet.accountsystem.jwt.JwtTokenService;
import com.pet.accountsystem.repository.UserRepository;
import com.pet.accountsystem.service.UserService;
import com.pet.accountsystem.service.auth.AuthenticationService;
import jakarta.servlet.ServletException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;
    private final JwtTokenService jwtTokenService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new DataNotFoundException("User not found"));

    }

    @Override
    public LoginResponseDto login(LoginDto loginDto) {
        UserEntity user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(() -> new DataNotFoundException("User not found"));
        if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            if (user.getIsActive()) {
                return LoginResponseDto.builder()
                        .userId(user.getId())
                        .role(user.getRole())
                        .accessToken(jwtTokenService.generateAccessToken(user))
                        .refreshToken(jwtTokenService.generateRefreshToken(user))
                        .build();
            }
            throw new NotAcceptableException("Your account has blocked");
        }
        throw new NotAcceptableException("Your password is incorrect or you are not signed in");
    }

    @Override
    public LoginResponseDto refresh(RefreshRequest refreshRequest) {
        try {
            String username= authenticationService.authenticateRefreshToken(refreshRequest);
            UserDetails userDetails = this.loadUserByUsername(username);
            return LoginResponseDto.builder()
                    .accessToken(jwtTokenService.generateAccessToken(userDetails))
                    .refreshToken(jwtTokenService.generateRefreshToken(userDetails))
                    .build();
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }

    }



}
