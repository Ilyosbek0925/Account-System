package com.pet.accountsystem.dto.response;

import com.pet.accountsystem.dto.enums.ClientType;
import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ClientResponseDTO {
    private UUID id;
    private String clientType;
    private String groupId;
    private UUID bazaId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Boolean isActive;
}
