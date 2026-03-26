package com.pet.accountsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "currency")
public class CurrencyEntity extends BaseEntity {
    private String usd;
    private long uzCache;
    private long uzTerminal;
    private long uzBank;
    private long uzClick;

}
