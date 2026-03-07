package com.pet.accountsystem.service;

import com.pet.accountsystem.dto.request.AgentBalanceRequestDTO;
import com.pet.accountsystem.dto.response.AgentBalanceResponseDTO;

import java.util.List;
import java.util.UUID;

public interface AgentBalanceService {
     AgentBalanceResponseDTO create(AgentBalanceRequestDTO dto);

   AgentBalanceResponseDTO getById(UUID id);

   List<AgentBalanceResponseDTO> getAll();

   AgentBalanceResponseDTO update(UUID id, AgentBalanceRequestDTO dto);

    void deleteById(UUID id);
}
