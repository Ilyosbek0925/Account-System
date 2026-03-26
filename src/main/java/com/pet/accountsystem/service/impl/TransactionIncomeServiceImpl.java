package com.pet.accountsystem.service.impl;

import com.pet.accountsystem.dto.TotalTransactionDTO;
import com.pet.accountsystem.dto.request.TransactionIncomeRequestDTO;
import com.pet.accountsystem.dto.response.TransactionIncomeResponse;
import com.pet.accountsystem.entity.*;
import com.pet.accountsystem.entity.enums.TransactionType;
import com.pet.accountsystem.exception.DataNotFoundException;
import com.pet.accountsystem.mapper.TransactionIncomeMapper;
import com.pet.accountsystem.mapper.TransactionTypeMapper;
import com.pet.accountsystem.projection.TransactionReportProjection;
import com.pet.accountsystem.repository.AgentRepository;
import com.pet.accountsystem.repository.ClientRepository;
import com.pet.accountsystem.repository.TransactionIncomeRepository;
import com.pet.accountsystem.repository.TransactionTypeRepository;
import com.pet.accountsystem.service.CurrencyService;
import com.pet.accountsystem.service.TransactionIncomeService;
import com.pet.accountsystem.specification.TransactionIncomeSpecification;
import com.pet.accountsystem.specification.TransactionTypeSpecification;
import com.pet.accountsystem.util.CalculatorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionIncomeServiceImpl implements TransactionIncomeService {

    private final TransactionIncomeRepository transactionIncomeRepository;
    private final AgentRepository agentRepository;
    private final ClientRepository clientRepository;
    private final TransactionIncomeMapper transactionIncomeMapper;
    private final CurrencyService currencyService;

    @Override
    @Transactional
    public TransactionIncomeResponse create(TransactionIncomeRequestDTO dto) {
        log.info("Creating transaction income agentId={}, clientId={}", dto.getAgentId(), dto.getClientId());

        Agent agent = agentRepository.findById(dto.getAgentId())
                .orElseThrow(() -> new DataNotFoundException("Agent not found: " + dto.getAgentId()));

        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new DataNotFoundException("Client not found: " + dto.getClientId()));

        TransactionIncome income = transactionIncomeMapper.toEntity(dto, agent, client);
        currencyService.setUsdAmountToUnitTransactions(income.getTypes());
        income.setTotal(CalculatorUtil.setTransactionIncomeTotalAmount(income.getTypes()));
        TransactionIncome saved = transactionIncomeRepository.save(income);
        log.info("Transaction income created id={}", saved.getId());
        return transactionIncomeMapper.toResponse(saved);
    }

    @Override
    public TransactionIncomeResponse getById(UUID id) {
        log.info("Fetching transaction income by id={}", id);

        TransactionIncome income = transactionIncomeRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("TransactionIncome not found: " + id));

        return transactionIncomeMapper.toResponse(income);
    }

    // client info

    @Override
    public List<TransactionIncomeResponse> getAllByAgentId(UUID agentId, LocalDate startDate, LocalDate endDate, TransactionType type, Pageable pageable) {

        Page<TransactionReportProjection> transactions = transactionIncomeRepository.findTransactions(agentId, type, startDate, endDate, pageable);

        transactions.forEach(transactionReportProjection -> System.out.println(transactionReportProjection.getUsdAmount()));
return null;
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
        return transactionIncomeMapper.toResponse(saved);
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