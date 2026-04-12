package com.pet.accountsystem.service;

import com.pet.accountsystem.dto.TotalTransactionDTO;
import com.pet.accountsystem.dto.request.TransactionIncomeRequestDTO;
import com.pet.accountsystem.dto.response.TransactionIncomeByRoleResponse;
import com.pet.accountsystem.dto.response.TransactionIncomeInfoResponse;
import com.pet.accountsystem.dto.response.TransactionIncomeResponse;
import com.pet.accountsystem.entity.enums.TransactionType;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TransactionIncomeService {
    TransactionIncomeResponse create(TransactionIncomeRequestDTO dto);

    TransactionIncomeInfoResponse getById(UUID id);


    TransactionIncomeResponse update(UUID id, TransactionIncomeRequestDTO dto);

    void deleteById(UUID id);

    List<TransactionIncomeByRoleResponse> getAllByAgentId(UUID agentId, LocalDate startDate, LocalDate endDate, TransactionType type, Pageable pageable);

     TotalTransactionDTO getTotalByAgentId(UUID agentId);

    List<TransactionIncomeByRoleResponse> getAllByClientId(UUID clientId, LocalDate fromDate, LocalDate toDate, TransactionType type, Pageable pageable);

    List<TransactionIncomeByRoleResponse> getAll(LocalDate fromDate, LocalDate toDate, TransactionType type, Pageable pageable);
}
