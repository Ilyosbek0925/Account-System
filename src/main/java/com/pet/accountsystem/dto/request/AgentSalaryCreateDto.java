package com.pet.accountsystem.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgentSalaryCreateDto {
    private BigDecimal salary;
    private Integer addedClients;
    private Integer kpiScore;
}