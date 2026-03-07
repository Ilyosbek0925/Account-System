package com.pet.accountsystem.dto.response;

import com.pet.accountsystem.entity.Role;
import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AdminResponseDTO {

    private UUID id;
    private String fullName;

    private String phoneNumber;

    private String email;

    private Role role;


    private boolean isActive;

    private String password;
}
