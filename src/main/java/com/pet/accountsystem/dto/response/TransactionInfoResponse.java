package com.pet.accountsystem.dto.response;

import com.pet.accountsystem.entity.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionInfoResponse {
    private UUID transactionId;
    private LocalDateTime transactionTime;

    private String clientFirstName;
    private String clientLastName;
    private TransactionType transactionType;
    private BigDecimal amound;
    private BigDecimal usdAmound;
    private String description;
    private Boolean isEditable;
}
