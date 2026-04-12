package com.pet.accountsystem.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AksiyaCreateDto {
    private String name;
    private LocalDate fromDate;
    private LocalDate toDate;
    private BigDecimal minAmount;
    private Integer bonus;
}
