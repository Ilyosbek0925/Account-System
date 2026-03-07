package com.pet.accountsystem.service;

import com.pet.accountsystem.dto.request.TransactionIncomeRequestDTO;
import com.pet.accountsystem.dto.response.TransactionIncomeResponseDTO;

import java.util.List;
import java.util.UUID;

public interface TransactionIncomeService {
   TransactionIncomeResponseDTO create(TransactionIncomeRequestDTO dto);

   TransactionIncomeResponseDTO getById(UUID id);

   List<TransactionIncomeResponseDTO> getAll();

   TransactionIncomeResponseDTO update(UUID id, TransactionIncomeRequestDTO dto);

    void deleteById(UUID id);
}
