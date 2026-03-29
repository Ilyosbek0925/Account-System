package com.pet.accountsystem.service;

import com.pet.accountsystem.dto.LoginDto;
import com.pet.accountsystem.dto.request.RefreshRequest;
import com.pet.accountsystem.dto.response.LoginResponseDto;

public interface UserService {
    LoginResponseDto login( LoginDto loginDto);

    LoginResponseDto refresh(RefreshRequest refreshRequest);
}
