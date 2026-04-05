package com.pet.accountsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
