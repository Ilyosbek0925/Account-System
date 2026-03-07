package com.pet.accountsystem.dto.request;

import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionIncomeRequestDTO {
    private UUID agentId;

    private UUID clientId;
    private String usdAmount;
    private String uzsAmount;
    private String clickAmount;
    private String bankAmount;

    private String description;
}
