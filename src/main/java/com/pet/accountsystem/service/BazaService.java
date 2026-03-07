package com.pet.accountsystem.service;

import com.pet.accountsystem.dto.request.BazaRequestDTO;
import com.pet.accountsystem.dto.response.BazaResponseDTO;

import java.util.List;
import java.util.UUID;

public interface BazaService {
    void deleteById(UUID id);

    BazaResponseDTO update(UUID id, BazaRequestDTO dto);

    List<BazaResponseDTO> getAll();

    BazaResponseDTO getById(UUID id);

    BazaResponseDTO create(BazaRequestDTO dto);
}
