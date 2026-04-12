package com.pet.accountsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AksiyaDto {
    private UUID id;
    private String name;
    private LocalDate fromDate;
    private LocalDate toDate;
    private BigDecimal minAmount;
    private Integer bonus;
}
