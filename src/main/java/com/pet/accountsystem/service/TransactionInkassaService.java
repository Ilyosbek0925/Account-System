package com.pet.accountsystem.service;

import com.pet.accountsystem.dto.request.TransactionInkassaRequestDTO;
import com.pet.accountsystem.dto.response.TransactionInkassaResponseDTO;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TransactionInkassaService {
    @Nullable TransactionInkassaResponseDTO create(TransactionInkassaRequestDTO dto);

    @Nullable TransactionInkassaResponseDTO getById(UUID id);

    @Nullable List<TransactionInkassaResponseDTO> getAll(LocalDate fromDate, LocalDate toDate, Pageable pageRequest);

    @Nullable TransactionInkassaResponseDTO update(UUID id, TransactionInkassaRequestDTO dto);

    void deleteById(UUID id);

    List<TransactionInkassaResponseDTO> getTransactionInkasssaByAdminId(UUID adminId, LocalDate fromDate, LocalDate toDate, Pageable pageRequest);

    List<TransactionInkassaResponseDTO> getTransactionInkasssaByAgentId(UUID agentId, LocalDate fromDate, LocalDate toDate, Pageable pageRequest);
}
