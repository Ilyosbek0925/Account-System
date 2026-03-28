package com.pet.accountsystem.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface TransactionReportProjection {
    String getFirstName();
    String getLastName();
    LocalDateTime getTransactionDate();
    BigDecimal getUsdAmount();
}