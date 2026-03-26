package com.pet.accountsystem.service;

import com.pet.accountsystem.dto.LoginDto;
import com.pet.accountsystem.dto.response.LoginResponseDto;
import jakarta.validation.Valid;

public interface UserService {
    LoginResponseDto login(@Valid LoginDto loginDto);
}
