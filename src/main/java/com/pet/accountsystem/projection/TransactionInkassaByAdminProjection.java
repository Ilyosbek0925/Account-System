package com.pet.accountsystem.projection;

import java.time.LocalDateTime;
import java.util.UUID;

public interface TransactionInkassaByAdminProjection {
    UUID getId();
    LocalDateTime getTransactionTime();
    String getAgentFirstName();
    String getAgentLastName();
    String getAgentPhoneNumber();
    UUID getAgentId();
    String getUsdAmount();
    String getUzsAmount();
    String getClickAmount();
    String getBankAmount();
    String getDescription();
}
