package com.pet.accountsystem.service;

import com.pet.accountsystem.dto.request.AgentRequestDTO;
import com.pet.accountsystem.dto.response.AgentResponseDTO;

import java.util.List;
import java.util.UUID;

public interface AgentService {
    AgentResponseDTO create(AgentRequestDTO requestDTO);

    AgentResponseDTO getById(UUID id);

    List<AgentResponseDTO> getAll();

    AgentResponseDTO update(UUID id, AgentRequestDTO requestDTO);

    void deleteById(UUID id);
}
