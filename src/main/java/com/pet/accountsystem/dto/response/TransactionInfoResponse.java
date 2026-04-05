package com.pet.accountsystem.dto.response;

import com.pet.accountsystem.entity.enums.TransactionType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionInfoResponse {
    private UUID transactionId;
    private LocalDateTime transactionTime;

    private String clientFirstName;
    private String clientLastName;
    private TransactionType transactionType;
    private List<UnitTransactionResponse> unitTransactionResponses;
    private String description;
    private Boolean isEditable;
}
