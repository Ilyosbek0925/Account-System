package com.pet.accountsystem.dto.response;

import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ClientResponseDTO {
    private UUID id;
    private String status;
    private String groupId;
    private String clientType;
    private UUID bazaId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private boolean isActive;
}
