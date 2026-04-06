package com.pet.accountsystem.mapper;

import com.pet.accountsystem.dto.request.TransactionIncomeRequestDTO;
import com.pet.accountsystem.dto.response.TransactionIncomeByRoleResponse;
import com.pet.accountsystem.dto.response.TransactionIncomeResponse;
import com.pet.accountsystem.dto.response.TransactionInfoResponse;
import com.pet.accountsystem.dto.response.UnitTransactionResponse;
import com.pet.accountsystem.entity.Agent;
import com.pet.accountsystem.entity.Client;
import com.pet.accountsystem.entity.TransactionIncome;
import com.pet.accountsystem.entity.UnitTransaction;
import com.pet.accountsystem.projection.TransactionAllTypeProjection;
import com.pet.accountsystem.projection.TransactionReportProjection;
import com.pet.accountsystem.util.FormatUtil;
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


    public TransactionIncomeByRoleResponse toTransactionAgentResponse(TransactionReportProjection projection) {
        return TransactionIncomeByRoleResponse.builder()
                .transactionId(projection.getId())
                .transactionDate(projection.getTransactionDate())
                .clientFirstName(projection.getFirstName())
                .clientLastName(projection.getLastName())
                .totalAmount(projection.getUsdAmount())
                .build();
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

    public TransactionIncomeByRoleResponse toTransactionAgentResponse(TransactionAllTypeProjection projection) {
        return TransactionIncomeByRoleResponse.builder()
                .transactionId(projection.getId())
                .transactionDate(projection.getTransactionDate())
                .clientFirstName(projection.getFirstName())
                .clientLastName(projection.getLastName())
                .totalAmount(projection.getUsdAmount())
                .types(projection.getTypes())
                .build();
    }


    public TransactionInfoResponse toTransactionInfoResponse(UnitTransaction unitTransaction) {
        TransactionIncome transactionIncome = unitTransaction.getTransactionIncome();
        Client client = transactionIncome.getClient();

        return null;

        //
//        return TransactionInfoResponse.builder()
//                .clientFirstName(client.getFirstName())
//                .clientLastName(client.getLastName())
//                .description(transactionIncome.getDescription())
//                .transactionTime(unitTransaction.getCreatedAt())
//                .unitTransactionResponses(toUnitTransactionResponse(unitTransaction))
//                .build();
    }



    private UnitTransactionResponse toUnitTransactionResponse(UnitTransaction unitTransaction){
        return UnitTransactionResponse.builder().usdAmount(FormatUtil.toScale(unitTransaction.getUsdAmount()))
                .type(unitTransaction.getTransactionType())
                .amount(FormatUtil.toScale(unitTransaction.getAmount()))
                .currency(unitTransaction.getCurrency()).build();
    }

}