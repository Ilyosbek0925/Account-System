package com.pet.accountsystem.service;

import com.pet.accountsystem.dto.LoginDto;
import com.pet.accountsystem.dto.response.LoginResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<LoginResponseDto> login(@Valid LoginDto loginDto);
}
