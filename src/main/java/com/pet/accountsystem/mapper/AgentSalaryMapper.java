package com.pet.accountsystem.mapper;

import com.pet.accountsystem.dto.AgentSalaryDto;
import com.pet.accountsystem.dto.request.AgentSalaryCreateDto;
import com.pet.accountsystem.entity.AgentSalary;
import org.springframework.stereotype.Component;

@Component
public class AgentSalaryMapper {

    public AgentSalary toEntity(AgentSalaryCreateDto dto) {
        AgentSalary entity = new AgentSalary();
        entity.setSalary(dto.getSalary());
        entity.setAddedClients(dto.getAddedClients());
        entity.setKpiScore(dto.getKpiScore());
        return entity;
    }

    public AgentSalaryDto toDto(AgentSalary entity) {
        return AgentSalaryDto.builder()
                .id(entity.getId())
                .salary(entity.getSalary())
                .addedClients(entity.getAddedClients())
                .kpiScore(entity.getKpiScore())
                .build();
    }

    public void updateEntity(AgentSalary entity, AgentSalaryDto dto) {
        entity.setSalary(dto.getSalary());
        entity.setAddedClients(dto.getAddedClients());
        entity.setKpiScore(dto.getKpiScore());
    }
}