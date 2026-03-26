package com.pet.accountsystem.projection;

import java.math.BigDecimal;

public interface TransactionReportProjection {
    String getFirstName();
    String getLastName();
    Long getTransactionDate();
    BigDecimal getUsdAmount();
}