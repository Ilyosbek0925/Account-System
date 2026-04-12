package com.pet.accountsystem.service;

import com.pet.accountsystem.dto.request.AgentBalanceRequestDTO;
import com.pet.accountsystem.dto.request.TransactionIncomeRequestDTO;
import com.pet.accountsystem.dto.request.TransactionInkassaRequestDTO;
import com.pet.accountsystem.dto.response.AgentBalanceResponseDTO;
import com.pet.accountsystem.entity.Agent;
import com.pet.accountsystem.entity.AgentBalance;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface AgentBalanceService {
     AgentBalanceResponseDTO create(AgentBalanceRequestDTO dto);


   List<AgentBalanceResponseDTO> getAll();

   AgentBalanceResponseDTO update(UUID id, AgentBalanceRequestDTO dto);

    void deleteById(UUID id);

    void addMoney(TransactionIncomeRequestDTO dto , Agent agent, BigDecimal total);

    AgentBalanceResponseDTO getAgentBalanceByAgentId(UUID agentId);

    void minusMoney(TransactionInkassaRequestDTO dto, AgentBalance agentBalance);
}
