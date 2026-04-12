package com.pet.accountsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
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
public class AgentSalary extends BaseEntity {
    private BigDecimal salary;
    private Integer addedClients;
    private Integer kpiScore;
    private BigDecimal totalTransactionAmount;

    @OneToOne
    private Agent agent;

}
