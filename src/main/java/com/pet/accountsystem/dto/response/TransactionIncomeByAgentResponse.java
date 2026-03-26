package com.pet.accountsystem.dto.response;

import com.pet.accountsystem.entity.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionIncomeByAgentResponse {
    private String clientFirstName;
    private String clientLastName;
    private LocalDate transactionDate;
    private List<TransactionType> types;
    private BigDecimal totalAmount;
}
