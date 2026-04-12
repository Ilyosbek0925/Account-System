package com.pet.accountsystem.service.impl;

import com.pet.accountsystem.dto.request.AgentBalanceRequestDTO;
import com.pet.accountsystem.dto.request.TransactionIncomeRequestDTO;
import com.pet.accountsystem.dto.request.TransactionInkassaRequestDTO;
import com.pet.accountsystem.dto.request.TransactionTypeRequest;
import com.pet.accountsystem.dto.response.AgentBalanceResponseDTO;
import com.pet.accountsystem.entity.Agent;
import com.pet.accountsystem.entity.AgentBalance;
import com.pet.accountsystem.entity.CurrencyEntity;
import com.pet.accountsystem.entity.enums.Currency;
import com.pet.accountsystem.exception.DataNotFoundException;
import com.pet.accountsystem.mapper.AgentBalanceMapper;
import com.pet.accountsystem.repository.AgentBalanceRepository;
import com.pet.accountsystem.repository.AgentRepository;
import com.pet.accountsystem.repository.CurrencyRepository;
import com.pet.accountsystem.service.AgentBalanceService;
import com.pet.accountsystem.util.CalculatorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private final CurrencyRepository currencyRepository;

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
    public void addMoney(TransactionIncomeRequestDTO dto, Agent agent, BigDecimal total) {

        AgentBalance agentBalance = agentBalanceRepository.findByAgent(agent)
                .orElseGet(() -> {
                    AgentBalance ab = new AgentBalance();
                    ab.setAgent(agent);
                    ab.setUsdAmount(BigDecimal.ZERO);
                    ab.setUzsAmount(BigDecimal.ZERO);
                    ab.setClickAmount(BigDecimal.ZERO);
                    ab.setBankAmount(BigDecimal.ZERO);
                    ab.setTotal(BigDecimal.ZERO);
                    return ab;
                });

        BigDecimal clickAmount = BigDecimal.ZERO;
        BigDecimal usdAmount = BigDecimal.ZERO;
        BigDecimal uzsAmount = BigDecimal.ZERO;
        BigDecimal bankAmount = BigDecimal.ZERO;

        for (TransactionTypeRequest typeRequest : dto.getTypeRequests()) {

            BigDecimal amount = safe(typeRequest.getAmount());

            switch (typeRequest.getType()) {

                case BANK -> bankAmount = bankAmount.add(amount);

                case CLICK -> clickAmount = clickAmount.add(amount);

                case CACHE -> {
                    if (typeRequest.getCurrency() == Currency.UZS) {
                        uzsAmount = uzsAmount.add(amount);
                    } else {
                        usdAmount = usdAmount.add(amount);
                    }
                }
            }
        }

        agentBalance.setUsdAmount(safe(agentBalance.getUsdAmount()).add(usdAmount));
        agentBalance.setBankAmount(safe(agentBalance.getBankAmount()).add(bankAmount));
        agentBalance.setUzsAmount(safe(agentBalance.getUzsAmount()).add(uzsAmount));
        agentBalance.setClickAmount(safe(agentBalance.getClickAmount()).add(clickAmount));
        agentBalance.setTotal(safe(agentBalance.getTotal()).add(safe(total)));

        agentBalanceRepository.save(agentBalance);
    }

    @Override
    public AgentBalanceResponseDTO getAgentBalanceByAgentId(UUID agentId) {
        Agent agent = agentRepository.findById(agentId).orElseThrow(() -> new DataNotFoundException("agent is not found"));
        Optional<AgentBalance> byAgent = agentBalanceRepository.findByAgent(agent);
        return byAgent.map(agentBalanceMapper::toResponse).orElse(null);
    }

    @Override
    public void minusMoney(TransactionInkassaRequestDTO dto, AgentBalance agentBalance) {

        CurrencyEntity currency = currencyRepository.findTopByOrderByCreatedAtDesc()
                .orElseThrow(() -> new DataNotFoundException("currency not found"));

        BigDecimal usd = dto.getUsdAmount() != null ? dto.getUsdAmount() : BigDecimal.ZERO;
        BigDecimal uzs = dto.getUzsAmount() != null ? dto.getUzsAmount() : BigDecimal.ZERO;
        BigDecimal click = dto.getClickAmount() != null ? dto.getClickAmount() : BigDecimal.ZERO;

        BigDecimal uzsToUsd = uzs.divide(
                BigDecimal.valueOf(currency.getUzBank()), 6, RoundingMode.HALF_UP
        );

        BigDecimal clickToUsd = click.divide(
                BigDecimal.valueOf(currency.getUzCache()), 6, RoundingMode.HALF_UP
        );

        BigDecimal totalMinus = usd
                .add(uzsToUsd)
                .add(clickToUsd);

        agentBalance.setUsdAmount(
                safe(agentBalance.getUsdAmount()).subtract(usd)
        );

        agentBalance.setUzsAmount(
                safe(agentBalance.getUzsAmount()).subtract(uzs)
        );

        agentBalance.setClickAmount(
                safe(agentBalance.getClickAmount()).subtract(click)
        );

        agentBalance.setTotal(
                safe(agentBalance.getTotal()).subtract(totalMinus)
        );

        agentBalanceRepository.save(agentBalance);
    }

    private BigDecimal safe(BigDecimal value) {
        return value != null ? value : BigDecimal.ZERO;
    }

}