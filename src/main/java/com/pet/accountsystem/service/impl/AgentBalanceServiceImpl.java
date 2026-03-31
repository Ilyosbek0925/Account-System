package com.pet.accountsystem.service.impl;

import com.pet.accountsystem.dto.request.AgentBalanceRequestDTO;
import com.pet.accountsystem.dto.request.TransactionIncomeRequestDTO;
import com.pet.accountsystem.dto.request.TransactionTypeRequest;
import com.pet.accountsystem.dto.response.AgentBalanceResponseDTO;
import com.pet.accountsystem.entity.Agent;
import com.pet.accountsystem.entity.AgentBalance;
import com.pet.accountsystem.entity.enums.Currency;
import com.pet.accountsystem.entity.enums.TransactionType;
import com.pet.accountsystem.exception.DataNotFoundException;
import com.pet.accountsystem.mapper.AgentBalanceMapper;
import com.pet.accountsystem.repository.AgentBalanceRepository;
import com.pet.accountsystem.repository.AgentRepository;
import com.pet.accountsystem.service.AgentBalanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AgentBalanceServiceImpl implements AgentBalanceService {

    private final AgentBalanceRepository agentBalanceRepository;
    private final AgentRepository agentRepository;
    private final AgentBalanceMapper agentBalanceMapper;

    @Override
    public AgentBalanceResponseDTO create(AgentBalanceRequestDTO dto) {
        log.info("Creating agent balance for agentId={}", dto.getAgentId());

        Agent agent = agentRepository.findById(dto.getAgentId())
                .orElseThrow(() -> new DataNotFoundException("Agent not found: " + dto.getAgentId()));

        AgentBalance balance = agentBalanceMapper.toEntity(dto, agent);
        AgentBalance saved = agentBalanceRepository.save(balance);

        log.info("Agent balance created with id={}", saved.getId());
        return agentBalanceMapper.toResponse(saved);
    }

//    @Override
//    public AgentBalanceResponseDTO getById(UUID id) {
//        log.info("Fetching agent balance by id={}", id);
//
//        AgentBalance balance = agentBalanceRepository.findById(id)
//                .orElseThrow(() -> new DataNotFoundException("AgentBalance not found: " + id));
//
//        return agentBalanceMapper.toResponse(balance);
//    }

    @Override
    public List<AgentBalanceResponseDTO> getAll() {
        log.info("Fetching all agent balances");

        return agentBalanceRepository.findAll()
                .stream()
                .map(agentBalanceMapper::toResponse)
                .toList();
    }

    @Override
    public AgentBalanceResponseDTO update(UUID id, AgentBalanceRequestDTO dto) {
        log.info("Updating agent balance id={}", id);

        AgentBalance balance = agentBalanceRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("AgentBalance not found: " + id));

        Agent agent = agentRepository.findById(dto.getAgentId())
                .orElseThrow(() -> new DataNotFoundException("Agent not found: " + dto.getAgentId()));

        agentBalanceMapper.updateEntity(dto, balance, agent);
        AgentBalance saved = agentBalanceRepository.save(balance);

        log.info("Agent balance updated id={}", saved.getId());
        return agentBalanceMapper.toResponse(saved);
    }

    @Override
    public void deleteById(UUID id) {
        log.info("Deleting agent balance id={}", id);

        if (!agentBalanceRepository.existsById(id)) {
            throw new DataNotFoundException("AgentBalance not found: " + id);
        }

        agentBalanceRepository.deleteById(id);
        log.info("Agent balance deleted id={}", id);
    }

    @Override
    public void addMoney(TransactionIncomeRequestDTO dto, Agent agent) {
        Optional<AgentBalance> byAgent = agentBalanceRepository.findByAgent(agent);
        BigDecimal clickAmount = BigDecimal.ZERO;
        BigDecimal usdAmount = BigDecimal.ZERO;
        BigDecimal uzsAmount = BigDecimal.ZERO;
        BigDecimal bankAmount = BigDecimal.ZERO;

        for (TransactionTypeRequest typeRequest : dto.getTypeRequests()) {
            switch (typeRequest.getType()) {
                case BANK:
                    bankAmount = bankAmount.add(typeRequest.getAmount());
                    break;

                case CLICK:
                    clickAmount = clickAmount.add(typeRequest.getAmount());
                    break;

                case CACHE:
                    if (typeRequest.getCurrency().equals(Currency.UZS)) {
                        uzsAmount = uzsAmount.add(typeRequest.getAmount());
                    } else {
                        usdAmount = usdAmount.add(typeRequest.getAmount());
                    }
                    break;
            }
        }


        if (byAgent.isEmpty()) {
            AgentBalance agentBalance = new AgentBalance();
            agentBalance.setUzsAmount(uzsAmount);
            agentBalance.setBankAmount(bankAmount);
            agentBalance.setClickAmount(clickAmount);
            agentBalance.setUsdAmount(usdAmount);
            agentBalance.setAgent(agent);
            agentBalanceRepository.save(agentBalance);
        } else {
            AgentBalance agentBalance = byAgent.get();
            agentBalance.setUsdAmount(agentBalance.getUsdAmount().add(usdAmount));
            agentBalance.setBankAmount(agentBalance.getBankAmount().add(bankAmount));
            agentBalance.setUzsAmount(agentBalance.getUzsAmount().add(uzsAmount));
            agentBalance.setClickAmount(agentBalance.getClickAmount().add(clickAmount));
            agentBalanceRepository.save(agentBalance);
        }

    }

    @Override
    public AgentBalanceResponseDTO getAgentBalanceByAgentId(UUID agentId) {
        Agent agent = agentRepository.findById(agentId).orElseThrow(() -> new DataNotFoundException("agent is not found"));
        Optional<AgentBalance> byAgent = agentBalanceRepository.findByAgent(agent);
        return byAgent.map(agentBalanceMapper::toResponse).orElse(null);
    }
}