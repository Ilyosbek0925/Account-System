package com.pet.accountsystem.projection;

import com.pet.accountsystem.entity.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public interface TransactionIncomeProjection {
    UUID Id();
    LocalDateTime createTransactionTime();
    LocalDateTime updateTransactionTime();
    String clientFirstName();
    String clientLastName();
    TransactionType transactionType();
    BigDecimal amound();
    BigDecimal usdAmound();
    String description();
}
