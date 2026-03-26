package com.pet.accountsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "admins_balances")
public class AdminBalance extends BaseEntity {

    private BigDecimal usdAmount;
    private BigDecimal uzsAmount;
    private BigDecimal clickAmount;
    private BigDecimal bankAmount;

    @OneToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
}
