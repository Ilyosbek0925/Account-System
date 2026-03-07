package com.pet.accountsystem.service;

import com.pet.accountsystem.dto.request.TransactionInkassaRequestDTO;
import com.pet.accountsystem.dto.response.TransactionInkassaResponseDTO;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public interface TransactionInkassaService {
    @Nullable TransactionInkassaResponseDTO create(TransactionInkassaRequestDTO dto);

    @Nullable TransactionInkassaResponseDTO getById(UUID id);

    @Nullable List<TransactionInkassaResponseDTO> getAll();

    @Nullable TransactionInkassaResponseDTO update(UUID id, TransactionInkassaRequestDTO dto);

    void deleteById(UUID id);
}
