package com.pet.accountsystem.dto.response;

import com.pet.accountsystem.entity.enums.Currency;
import com.pet.accountsystem.entity.enums.TransactionType;
import lombok.*;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UnitTransactionResponse {
    private UUID id;
    private String amount;
    private String usdAmount;
    private TransactionType type;
    private Currency currency;
}
