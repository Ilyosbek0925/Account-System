package com.pet.accountsystem.dto.request;

import com.pet.accountsystem.entity.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AgentRequestDTO {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String phoneNumber;
    @Email
    private String email;
    @NotNull
    private Role role;
    @NotNull
    private Boolean isActive;
    @NotBlank
    private String password;

    private UUID bazaId;
}
