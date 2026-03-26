package com.pet.accountsystem.dto.response;

import com.pet.accountsystem.entity.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class LoginResponseDto {
    private UUID userId;
    private String jwtToken;
    private Role role;
}
