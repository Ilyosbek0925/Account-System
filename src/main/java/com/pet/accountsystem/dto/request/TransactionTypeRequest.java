package com.pet.accountsystem.dto.request;

import com.pet.accountsystem.entity.enums.Currency;
import com.pet.accountsystem.entity.enums.TransactionType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionTypeRequest {
    @NotNull
    private BigDecimal amount;
    @NotNull
    private TransactionType type;
    private Currency currency;

}
