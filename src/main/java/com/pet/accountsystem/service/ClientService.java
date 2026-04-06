package com.pet.accountsystem.service;

import com.pet.accountsystem.dto.request.ClientRequestDTO;
import com.pet.accountsystem.dto.response.ClientResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ClientService {


    ClientResponseDTO createClient(ClientRequestDTO requestDTO);

    void deleteById(UUID id);

    ClientResponseDTO getById(UUID id);

    List<ClientResponseDTO> getAll();

    ClientResponseDTO update(UUID id, ClientRequestDTO requestDTO);

    List<ClientResponseDTO> getByAgent(UUID agentId, Pageable pageable);
}
