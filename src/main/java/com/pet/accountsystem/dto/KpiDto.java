package com.pet.accountsystem.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KpiDto {
    private UUID id;
    private String bankKpi;
    private String cacheKpi;
    private Integer bonus;
    private Double monthlyTarget;
}