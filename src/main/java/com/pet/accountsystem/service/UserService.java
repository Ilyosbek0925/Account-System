package com.pet.accountsystem.service;

import com.pet.accountsystem.dto.LoginDto;
import com.pet.accountsystem.dto.response.LoginResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

public interface UserService {
    LoginResponseDto login(@Valid LoginDto loginDto);

    LoginResponseDto refresh(HttpServletRequest request, HttpServletResponse response);
}
