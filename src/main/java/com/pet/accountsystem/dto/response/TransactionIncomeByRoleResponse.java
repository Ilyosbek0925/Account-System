package com.pet.accountsystem.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionIncomeByRoleResponse {
    private UUID transactionId;
    private String clientFirstName;
    private String clientLastName;
    private LocalDateTime transactionDate;
    private String types;
    private BigDecimal totalAmount;
}
