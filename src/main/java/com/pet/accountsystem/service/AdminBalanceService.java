package com.pet.accountsystem.service;

import com.pet.accountsystem.dto.request.AdminBalanceRequestDTO;
import com.pet.accountsystem.dto.request.TransactionInkassaRequestDTO;
import com.pet.accountsystem.dto.response.AdminBalanceResponseDTO;
import com.pet.accountsystem.entity.AdminBalance;

import java.util.List;
import java.util.UUID;

public interface AdminBalanceService {
    AdminBalanceResponseDTO create(AdminBalanceRequestDTO dto);

    AdminBalanceResponseDTO getById(UUID id);

    List<AdminBalanceResponseDTO> getAll();

    AdminBalanceResponseDTO update(UUID id, AdminBalanceRequestDTO dto);

    void deleteById(UUID id);

    void addMoney(TransactionInkassaRequestDTO dto, AdminBalance adminBalance);

}
