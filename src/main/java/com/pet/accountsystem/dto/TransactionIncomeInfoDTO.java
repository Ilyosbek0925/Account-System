package com.pet.accountsystem.dto;

import com.pet.accountsystem.entity.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TransactionIncomeInfoDTO {
    private String firstName;
    private String lastName;
    private LocalDate transactionDate;
    private List<TransactionType>types;
    private BigDecimal totalAmount;
}
