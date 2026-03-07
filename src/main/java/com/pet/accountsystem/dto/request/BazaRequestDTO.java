package com.pet.accountsystem.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BazaRequestDTO {

    private String region;
    private String district;

    private String groupId;
}
