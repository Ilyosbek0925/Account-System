package com.pet.accountsystem.service.impl;

import com.pet.accountsystem.dto.request.TransactionTypeRequest;
import com.pet.accountsystem.entity.TransactionIncome;
import com.pet.accountsystem.entity.UnitTransaction;
import com.pet.accountsystem.mapper.TransactionTypeMapper;
import com.pet.accountsystem.repository.TransactionTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionTypeServiceImpl {

//    private final TransactionTypeMapper typeMapper;
//    private final TransactionTypeRepository repository;
//
//    @Transactional
//    public void saveAll(List<TransactionTypeRequest> typeRequests, TransactionIncome transactionIncome) {
//        List<UnitTransaction> list = typeRequests.stream().map(typeMapper::toTransactionType).toList();
//        repository.saveAll(list);
//    }
}
