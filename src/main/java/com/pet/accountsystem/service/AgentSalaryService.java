package com.pet.accountsystem.service;

import com.pet.accountsystem.dto.AgentSalaryDto;
import com.pet.accountsystem.dto.request.AgentSalaryCreateDto;
import com.pet.accountsystem.dto.request.TransactionIncomeRequestDTO;
import com.pet.accountsystem.entity.Agent;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface AgentSalaryService {

    AgentSalaryDto create(AgentSalaryCreateDto dto);

    List<AgentSalaryDto> getAll();

    AgentSalaryDto getById(UUID id);

    AgentSalaryDto update(AgentSalaryDto dto);

    void delete(UUID id);

    void addSalary(TransactionIncomeRequestDTO dto, Agent agent, BigDecimal total);
}