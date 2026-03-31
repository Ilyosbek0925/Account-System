package com.pet.accountsystem.projection;

import java.math.BigDecimal;

public interface TransactionTypeSummary {
    String getTransactionType();

    BigDecimal getTotalAmount();

    BigDecimal getTotalUsdAmount();
}