package com.pet.accountsystem.dto.request;

import com.pet.accountsystem.entity.enums.Role;
import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ClientRequestDTO {
    private String firstName;
    private String lastName;
    private String status;
    private String groupId;
    private String clientType;
    private UUID bazaId;
    private String phoneNumber;
    private String email;
    private boolean isActive;


}
