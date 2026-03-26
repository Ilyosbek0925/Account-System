package com.pet.accountsystem.dto.request;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionInkassaRequestDTO {
    private UUID adminId;

    private UUID agentId;

    private BigDecimal usdAmount;
    private BigDecimal uzsAmount;
    private BigDecimal clickAmount;
    private BigDecimal bankAmount;

    private String description;
}
