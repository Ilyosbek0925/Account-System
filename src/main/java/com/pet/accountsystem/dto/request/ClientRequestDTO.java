package com.pet.accountsystem.dto.request;

import com.pet.accountsystem.entity.Baza;
import com.pet.accountsystem.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ClientRequestDTO {
    private String status;
    private String groupId;
    private String clientType;
    private UUID bazaId;
    private String fullName;
    private String phoneNumber;
    private String email;
    private Role role;
    private boolean isActive;
    private String password;


}
