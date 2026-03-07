package com.pet.accountsystem.service;

import com.pet.accountsystem.dto.LoginDto;
import com.pet.accountsystem.dto.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<ApiResponse<?>> login(@Valid LoginDto loginDto);
}
