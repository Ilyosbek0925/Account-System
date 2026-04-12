package com.pet.accountsystem.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Kpi extends BaseEntity{
    private String bankKpi;
    private String cacheKpi;
    private Integer bonus;
    private Double monthlyTarget;

}
