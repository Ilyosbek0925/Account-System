package com.pet.accountsystem.mapper;

import com.pet.accountsystem.dto.request.TransactionIncomeRequestDTO;
import com.pet.accountsystem.dto.response.TransactionIncomeResponseDTO;
import com.pet.accountsystem.entity.Agent;
import com.pet.accountsystem.entity.Client;
import com.pet.accountsystem.entity.TransactionIncome;
import org.springframework.stereotype.Component;

@Component
public class TransactionIncomeMapper {

    public TransactionIncome toEntity(TransactionIncomeRequestDTO dto,
                                      Agent agent,
                                      Client client) {
        if (dto == null) return null;

        TransactionIncome income = new TransactionIncome();
        income.setAgent(agent);
        income.setClient(client);
        income.setUsdAmount(dto.getUsdAmount());
        income.setUzsAmount(dto.getUzsAmount());
        income.setClickAmount(dto.getClickAmount());
        income.setBankAmount(dto.getBankAmount());
        income.setDescription(dto.getDescription());
        return income;
    }

    public TransactionIncomeResponseDTO toResponse(TransactionIncome income) {
        if (income == null) return null;

        TransactionIncomeResponseDTO dto = new TransactionIncomeResponseDTO();
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
        dto.setUsdAmount(income.getUsdAmount());
        dto.setUzsAmount(income.getUzsAmount());
        dto.setClickAmount(income.getClickAmount());
        dto.setBankAmount(income.getBankAmount());
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
        income.setUsdAmount(dto.getUsdAmount());
        income.setUzsAmount(dto.getUzsAmount());
        income.setClickAmount(dto.getClickAmount());
        income.setBankAmount(dto.getBankAmount());
        income.setDescription(dto.getDescription());
    }
}