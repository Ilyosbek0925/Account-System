package com.pet.accountsystem.service.impl;

import com.pet.accountsystem.dto.LoginDto;
import com.pet.accountsystem.dto.response.ApiResponse;
import com.pet.accountsystem.entity.UserEntity;
import com.pet.accountsystem.exception.DataNotFoundException;
import com.pet.accountsystem.exception.NotAcceptableException;
import com.pet.accountsystem.jwt.JwtTokenService;
import com.pet.accountsystem.repository.UserRepository;
import com.pet.accountsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final JwtTokenService jwtTokenService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new DataNotFoundException("User not found"));

    }
    @Override
    public ResponseEntity<ApiResponse<?>> login(LoginDto loginDto) {
        UserEntity user = userRepository.findByEmail(loginDto.getMail()).orElseThrow(() -> new DataNotFoundException("User not found"));
        if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            if (user.isActive()) {
                return ResponseEntity.ok(ApiResponse.builder()
                        .data((jwtTokenService.generateAccessToken(user)))
                        .message("Login in system")
                        .status(200)
                        .build());
            }
            throw new NotAcceptableException("Your account has blocked");
        }
        throw new NotAcceptableException("Your password is incorrect or you are not signed in");
    }
}
