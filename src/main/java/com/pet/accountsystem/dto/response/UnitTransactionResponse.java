package com.pet.accountsystem.dto.response;

import com.pet.accountsystem.entity.enums.Currency;
import com.pet.accountsystem.entity.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnitTransactionResponse {
    private UUID id;
    private String amount;
    private String usdAmount;
    private TransactionType type;
    private Currency currency;
}
