package com.pet.accountsystem.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public interface TransactionReportProjection {
    UUID getId();
    String getFirstName();
    String getLastName();
    LocalDateTime getTransactionDate();
    BigDecimal getUsdAmount();
}