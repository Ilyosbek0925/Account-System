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
@Table(name = "agents_balances")
public class AgentBalance extends BaseEntity {

    private BigDecimal usdAmount;
    private BigDecimal uzsAmount;
    private BigDecimal clickAmount;
    private BigDecimal bankAmount;

    @OneToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;
}