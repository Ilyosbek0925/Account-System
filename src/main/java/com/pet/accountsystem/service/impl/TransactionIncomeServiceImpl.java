package com.pet.accountsystem.service.impl;

import com.pet.accountsystem.dto.request.TransactionIncomeRequestDTO;
import com.pet.accountsystem.dto.response.TransactionIncomeResponseDTO;
import com.pet.accountsystem.entity.Agent;
import com.pet.accountsystem.entity.Client;
import com.pet.accountsystem.entity.TransactionIncome;
import com.pet.accountsystem.exception.DataNotFoundException;
import com.pet.accountsystem.mapper.TransactionIncomeMapper;
import com.pet.accountsystem.repository.AgentRepository;
import com.pet.accountsystem.repository.ClientRepository;
import com.pet.accountsystem.repository.TransactionIncomeRepository;
import com.pet.accountsystem.service.TransactionIncomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    @Override
    public TransactionIncomeResponseDTO create(TransactionIncomeRequestDTO dto) {
        log.info("Creating transaction income agentId={}, clientId={}", dto.getAgentId(), dto.getClientId());

        Agent agent = agentRepository.findById(dto.getAgentId())
                .orElseThrow(() -> new DataNotFoundException("Agent not found: " + dto.getAgentId()));

        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new DataNotFoundException("Client not found: " + dto.getClientId()));

        TransactionIncome income = transactionIncomeMapper.toEntity(dto, agent, client);
        TransactionIncome saved = transactionIncomeRepository.save(income);

        log.info("Transaction income created id={}", saved.getId());
        return transactionIncomeMapper.toResponse(saved);
    }

    @Override
    public TransactionIncomeResponseDTO getById(UUID id) {
        log.info("Fetching transaction income by id={}", id);

        TransactionIncome income = transactionIncomeRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("TransactionIncome not found: " + id));

        return transactionIncomeMapper.toResponse(income);
    }

    @Override
    public List<TransactionIncomeResponseDTO> getAll() {
        log.info("Fetching all transaction incomes");

        return transactionIncomeRepository.findAll()
                .stream()
                .map(transactionIncomeMapper::toResponse)
                .toList();
    }

    @Override
    public TransactionIncomeResponseDTO update(UUID id, TransactionIncomeRequestDTO dto) {
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