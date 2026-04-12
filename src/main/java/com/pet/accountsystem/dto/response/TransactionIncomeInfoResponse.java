package com.pet.accountsystem.dto.response;

import com.pet.accountsystem.entity.enums.TransactionType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionIncomeInfoResponse {
    private UUID id;
    private LocalDateTime transactionTime;

    private String clientFirstName;
    private String clientLastName;
    private List<UnitTransactionResponse> unitTransactionResponses;
    private String description;
    private Boolean isEditable;
}
