package com.pet.accountsystem.mapper;

import com.pet.accountsystem.dto.KpiDto;
import com.pet.accountsystem.dto.request.KpiCreateDto;
import com.pet.accountsystem.entity.Kpi;
import org.springframework.stereotype.Component;

@Component
public class KpiMapper {

    public Kpi toEntity(KpiCreateDto dto) {
        Kpi entity = new Kpi();
        entity.setBankKpi(dto.getBankKpi());
        entity.setCacheKpi(dto.getCacheKpi());
        entity.setBonus(dto.getBonus());
        entity.setMonthlyTarget(dto.getMonthlyTarget());
        return entity;
    }

    public KpiDto toDto(Kpi entity) {
        return KpiDto.builder()
                .id(entity.getId())
                .bankKpi(entity.getBankKpi())
                .cacheKpi(entity.getCacheKpi())
                .bonus(entity.getBonus())
                .monthlyTarget(entity.getMonthlyTarget())
                .build();
    }

    public void updateEntity(Kpi entity, KpiDto dto) {
        entity.setBankKpi(dto.getBankKpi());
        entity.setCacheKpi(dto.getCacheKpi());
        entity.setBonus(dto.getBonus());
        entity.setMonthlyTarget(dto.getMonthlyTarget());
    }
}