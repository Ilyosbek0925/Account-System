package com.pet.accountsystem.dto.request;

import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionInkassaRequestDTO {
    private UUID adminId;

    private UUID agentId;

    private String usdAmount;
    private String uzsAmount;
    private String clickAmount;
    private String bankAmount;

    private String description;
}
