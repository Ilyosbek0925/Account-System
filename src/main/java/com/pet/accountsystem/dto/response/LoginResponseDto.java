package com.pet.accountsystem.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pet.accountsystem.entity.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponseDto {
    private UUID userId;
    private String accessToken;
    private String refreshToken;
    private Role role;
}
