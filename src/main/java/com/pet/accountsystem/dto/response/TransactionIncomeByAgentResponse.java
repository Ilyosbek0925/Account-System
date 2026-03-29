package com.pet.accountsystem.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pet.accountsystem.entity.enums.TransactionType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionIncomeByAgentResponse {
    private String clientFirstName;
    private String clientLastName;
    private LocalDateTime transactionDate;
    private String types;
    private BigDecimal totalAmount;
}
