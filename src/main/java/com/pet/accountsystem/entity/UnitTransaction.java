package com.pet.accountsystem.entity;

import com.pet.accountsystem.entity.enums.Currency;
import com.pet.accountsystem.entity.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "unit_transactions")
public class UnitTransaction extends BaseEntity {

    private BigDecimal amount;

    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @ManyToOne(fetch = FetchType.EAGER)
    private TransactionIncome transactionIncome;

    @Enumerated(EnumType.STRING)
    private Currency currency;


    private BigDecimal usdAmount;

}
