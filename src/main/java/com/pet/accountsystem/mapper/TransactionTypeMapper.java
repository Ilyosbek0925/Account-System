package com.pet.accountsystem.mapper;

import com.pet.accountsystem.dto.request.TransactionTypeRequest;
import com.pet.accountsystem.dto.response.TransactionIncomeResponse;
import com.pet.accountsystem.entity.TransactionIncome;
import com.pet.accountsystem.entity.UnitTransaction;
import com.pet.accountsystem.entity.enums.TransactionType;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.util.List;

@Component
public class TransactionTypeMapper {


    public UnitTransaction toTransactionType(TransactionTypeRequest request, TransactionIncome saved) {
        UnitTransaction entity = new UnitTransaction();
        entity.setTransactionType(request.getType());
        entity.setAmount(request.getAmount());
        entity.setCurrency(request.getCurrency());
        entity.setTransactionIncome(saved);
        return entity;
    }


    public TransactionIncomeResponse toTransactionIncomeResponse(UnitTransaction entity, TransactionType type) {
        TransactionIncome transactionIncome = entity.getTransactionIncome();
        TransactionIncomeResponse dto = new TransactionIncomeResponse();
        dto.setId(entity.getId());
        dto.setAgentId(
                transactionIncome.getAgent().getId()
        );
        dto.setClientId(
                transactionIncome.getClient().getId()
        );

        dto.setFirstName(transactionIncome.getClient().getFirstName());
        dto.setFirstName(transactionIncome.getClient().getLastName());
        dto.setTotalAmount(entity.getAmount().setScale(3, RoundingMode.DOWN).toString());
        dto.setTypes(List.of(type));
        dto.setDescription(transactionIncome.getDescription());
        return dto;
    }
}
