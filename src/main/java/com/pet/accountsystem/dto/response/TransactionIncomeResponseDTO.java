package com.pet.accountsystem.dto.response;

import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionIncomeResponseDTO {
    private UUID id;
    private UUID agentId;

    private UUID clientId;
    private String usdAmount;
    private String uzsAmount;
    private String clickAmount;
    private String bankAmount;

    private String description;
}
