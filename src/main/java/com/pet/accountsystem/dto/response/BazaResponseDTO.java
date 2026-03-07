package com.pet.accountsystem.dto.response;

import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BazaResponseDTO {
    private UUID id;
    private String region;
    private String district;
    private String groupId;

}
