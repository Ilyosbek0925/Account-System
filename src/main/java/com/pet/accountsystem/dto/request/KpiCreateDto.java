package com.pet.accountsystem.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KpiCreateDto {
    private String bankKpi;
    private String cacheKpi;
    private Integer bonus;
    private Double monthlyTarget;
}