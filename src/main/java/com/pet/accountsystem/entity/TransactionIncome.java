package com.pet.accountsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "transaction_income")
public class TransactionIncome extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id")
    private Agent agent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    private String description;

    private BigDecimal total;


    @OneToMany(mappedBy = "transactionIncome", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<UnitTransaction>types;

}