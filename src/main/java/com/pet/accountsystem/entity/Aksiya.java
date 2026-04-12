package com.pet.accountsystem.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Aksiya extends BaseEntity {
    private String name;
    private LocalDate fromDate;
    private LocalDate toDate;
    private BigDecimal minAmount;
    private Integer bonus;

}
