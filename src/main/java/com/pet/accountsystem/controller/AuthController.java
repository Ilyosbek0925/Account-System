package com.pet.accountsystem.controller;
import com.pet.accountsystem.dto.LoginDto;
import com.pet.accountsystem.dto.response.ApiResponse;
import com.pet.accountsystem.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@Valid @RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }

}
