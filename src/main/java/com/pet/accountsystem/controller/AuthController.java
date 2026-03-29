package com.pet.accountsystem.controller;

import com.pet.accountsystem.dto.LoginDto;
import com.pet.accountsystem.dto.request.RefreshRequest;
import com.pet.accountsystem.dto.response.ApiResponse;
import com.pet.accountsystem.dto.response.LoginResponseDto;
import com.pet.accountsystem.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDto>> login(@Valid @RequestBody LoginDto loginDto) {

        LoginResponseDto response = userService.login(loginDto);

        return ResponseEntity.ok(
                ApiResponse.<LoginResponseDto>builder()
                        .message("Login successful")
                        .data(response)
                        .status(200)
                        .build()
        );
    }



    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<LoginResponseDto>> refresh(@RequestBody RefreshRequest refreshRequest) {

        LoginResponseDto refresh = userService.refresh(refreshRequest);
        return ResponseEntity.ok(
                ApiResponse.<LoginResponseDto>builder()
                        .message("The token is updated")
                        .data(refresh)
                        .status(200)
                        .build()
        );
    }


}