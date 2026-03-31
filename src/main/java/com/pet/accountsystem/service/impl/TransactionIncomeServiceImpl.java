package com.pet.accountsystem.service.impl;

import com.pet.accountsystem.dto.TotalTransactionDTO;
import com.pet.accountsystem.dto.request.TransactionIncomeRequestDTO;
import com.pet.accountsystem.dto.response.TransactionIncomeByAgentResponse;
import com.pet.accountsystem.dto.response.TransactionIncomeResponse;
import com.pet.accountsystem.entity.*;
import com.pet.accountsystem.entity.enums.TransactionType;
import com.pet.accountsystem.exception.DataNotFoundException;
import com.pet.accountsystem.mapper.TransactionIncomeMapper;
import com.pet.accountsystem.mapper.TransactionTypeMapper;
import com.pet.accountsystem.projection.TransactionAllTypeProjection;
import com.pet.accountsystem.projection.TransactionReportProjection;
import com.pet.accountsystem.repository.AgentRepository;
import com.pet.accountsystem.repository.ClientRepository;
import com.pet.accountsystem.repository.TransactionIncomeRepository;
import com.pet.accountsystem.repository.TransactionTypeRepository;
import com.pet.accountsystem.service.AgentBalanceService;
import com.pet.accountsystem.service.CurrencyService;
import com.pet.accountsystem.service.TransactionIncomeService;
import com.pet.accountsystem.util.CalculatorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor

@Slf4j
public class TransactionIncomeServiceImpl implements TransactionIncomeService {
    private final TransactionTypeRepository unitTransactionRepository;
    private final TransactionTypeMapper typeMapper;
    private final TransactionIncomeRepository transactionIncomeRepository;
    private final AgentRepository agentRepository;
    private final ClientRepository clientRepository;
    private final TransactionIncomeMapper transactionIncomeMapper;
    private final CurrencyService currencyService;
    private final AgentBalanceService agentBalanceService;

    @Override
    @Transactional
    public TransactionIncomeResponse create(TransactionIncomeRequestDTO dto) {
        log.info("Creating transaction income agentId={}, clientId={}", dto.getAgentId(), dto.getClientId());

        Agent agent = agentRepository.findById(dto.getAgentId())
                .orElseThrow(() -> new DataNotFoundException("Agent not found: " + dto.getAgentId()));

        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new DataNotFoundException("Client not found: " + dto.getClientId()));

        TransactionIncome transactionIncome = transactionIncomeMapper.toEntity(dto, agent, client);
        TransactionIncome income = transactionIncomeRepository.save(transactionIncome);
        List<UnitTransaction> list = dto.getTypeRequests().stream().map(request -> typeMapper.toTransactionType(request, income)).toList();
        currencyService.setUsdAmountToUnitTransactions(list);
        income.setTotal(CalculatorUtil.setTransactionIncomeTotalAmount(list));
        transactionIncomeRepository.save(income);
        List<UnitTransaction> saved = unitTransactionRepository.saveAll(list);

        agentBalanceService.addMoney(dto, agent);

        log.info("Transaction income created id={}", saved.get(0).getId());
        return transactionIncomeMapper.toResponse(saved.get(0).getTransactionIncome(), list);
    }

    @Override
    public TransactionIncomeResponse getById(UUID id) {
        log.info("Fetching transaction income by id={}", id);

        TransactionIncome income = transactionIncomeRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("TransactionIncome not found: " + id));


//        transactionIncomeMapper.toTransactionInfoResponse(income);


        return null;
    }


    @Override
    public List<TransactionIncomeByAgentResponse> getAllByAgentId(UUID agentId, LocalDate startDate, LocalDate endDate, TransactionType type, Pageable pageable) {
        LocalDateTime fromDateTime = null;
        LocalDateTime toDateTime = null;
        if (startDate != null) {
            fromDateTime = startDate.atStartOfDay();
        }
        if (endDate != null) {
            toDateTime = endDate.atTime(23, 59, 59);
        }
        System.out.println(type);

        if (type == null) {
            System.out.println("come to type");
            Page<TransactionAllTypeProjection> allTypeProjections = transactionIncomeRepository.findTransactionsByAllTypes(agentId, fromDateTime, toDateTime, pageable);
            allTypeProjections.forEach(projection -> System.out.println(projection.getTypes()));
            System.out.println("last");
            return allTypeProjections.stream().map(transactionIncomeMapper::toTransactionAgentResponse).toList();
        }


        Page<TransactionReportProjection> transactions = transactionIncomeRepository.findTransactions(agentId, type.toString(), fromDateTime, toDateTime, pageable);
        return transactions.stream().map(transactionIncomeMapper::toTransactionAgentResponse).toList();
    }

    @Override
    public TotalTransactionDTO getTotalByAgentId(UUID agentId) {
        return transactionIncomeRepository.getTotalTransaction(agentId);
    }

    @Override
    public TransactionIncomeResponse update(UUID id, TransactionIncomeRequestDTO dto) {
        log.info("Updating transaction income id={}", id);

        TransactionIncome income = transactionIncomeRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("TransactionIncome not found: " + id));

        Agent agent = agentRepository.findById(dto.getAgentId())
                .orElseThrow(() -> new DataNotFoundException("Agent not found: " + dto.getAgentId()));

        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new DataNotFoundException("Client not found: " + dto.getClientId()));

        transactionIncomeMapper.updateEntity(dto, income, agent, client);
        TransactionIncome saved = transactionIncomeRepository.save(income);

        log.info("Transaction income updated id={}", saved.getId());
        return null;
    }

    @Override
    public void deleteById(UUID id) {
        log.info("Deleting transaction income id={}", id);

        TransactionIncome income = transactionIncomeRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("TransactionIncome not found: " + id));

        transactionIncomeRepository.delete(income);

        log.info("Transaction income deleted id={}", id);
    }
}