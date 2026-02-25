package com.pet.accountsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AdminBalance extends BaseEntity {

    private java.math.BigDecimal usdAmount;
    private java.math.BigDecimal uzsAmount;
    private java.math.BigDecimal clickAmount;
    private java.math.BigDecimal bankAmount;

    @OneToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
}
