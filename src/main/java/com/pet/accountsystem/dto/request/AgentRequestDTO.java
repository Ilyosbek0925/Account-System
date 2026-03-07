package com.pet.accountsystem.dto.request;

import com.pet.accountsystem.entity.Role;
import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AgentRequestDTO {
    private String fullName;
    private String phoneNumber;

    private String email;

    private Role role;


    private boolean isActive;

    private String password;

    private UUID bazaId;
}
