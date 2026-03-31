package com.pet.accountsystem.dto.request;

import com.pet.accountsystem.dto.enums.ClientType;
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
    private String groupId;
    private ClientType clientType;
    private UUID bazaId;
    private String phoneNumber;
    private Boolean isActive;
}
