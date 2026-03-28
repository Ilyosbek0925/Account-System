package com.pet.accountsystem.mapper;

import com.pet.accountsystem.dto.request.TransactionIncomeRequestDTO;
import com.pet.accountsystem.dto.request.TransactionTypeRequest;
import com.pet.accountsystem.dto.response.TransactionIncomeResponse;
import com.pet.accountsystem.entity.Agent;
import com.pet.accountsystem.entity.Client;
import com.pet.accountsystem.entity.TransactionIncome;
import com.pet.accountsystem.entity.UnitTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TransactionIncomeMapper {

    private final TransactionTypeMapper typeMapper;


    public TransactionIncome toEntity(TransactionIncomeRequestDTO dto,
                                      Agent agent,
                                      Client client) {
        TransactionIncome income = new TransactionIncome();
        income.setAgent(agent);
        income.setClient(client);
        income.setDescription(dto.getDescription());
        return income;
    }

    public TransactionIncomeResponse toResponse(TransactionIncome income, List<UnitTransaction> unitTransactions) {
        if (income == null) return null;

        TransactionIncomeResponse dto = new TransactionIncomeResponse();
        dto.setId(income.getId());
        dto.setAgentId(
                income.getAgent() != null
                        ? income.getAgent().getId()
                        : null
        );
        dto.setClientId(
                income.getClient() != null
                        ? income.getClient().getId()
                        : null
        );


        dto.setTotalAmount(income.getTotal().setScale(3, RoundingMode.DOWN).toString());
        dto.setTypes(unitTransactions.stream().map(UnitTransaction::getTransactionType).toList());
        dto.setDescription(income.getDescription());
        return dto;
    }

    public void updateEntity(TransactionIncomeRequestDTO dto,
                             TransactionIncome income,
                             Agent agent,
                             Client client) {
        if (dto == null || income == null) return;

        income.setAgent(agent);
        income.setClient(client);

        income.setDescription(dto.getDescription());
    }
}