package com.pet.accountsystem.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgentSalaryDto {
    private UUID id;
    private BigDecimal salary;
    private Integer addedClients;
    private Integer kpiScore;
}