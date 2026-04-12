package com.pet.accountsystem.service.impl;

import com.pet.accountsystem.dto.AgentSalaryDto;
import com.pet.accountsystem.dto.request.AgentSalaryCreateDto;
import com.pet.accountsystem.dto.request.TransactionIncomeRequestDTO;
import com.pet.accountsystem.entity.Agent;
import com.pet.accountsystem.entity.AgentSalary;
import com.pet.accountsystem.entity.CurrencyEntity;
import com.pet.accountsystem.entity.Kpi;
import com.pet.accountsystem.entity.enums.Currency;
import com.pet.accountsystem.exception.DataNotFoundException;
import com.pet.accountsystem.mapper.AgentSalaryMapper;
import com.pet.accountsystem.repository.AgentRepository;
import com.pet.accountsystem.repository.AgentSalaryRepository;
import com.pet.accountsystem.repository.CurrencyRepository;
import com.pet.accountsystem.repository.KpiRepository;
import com.pet.accountsystem.service.AgentSalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgentSalaryServiceImpl implements AgentSalaryService {

    private final AgentRepository agentRepository;
    private final AgentSalaryRepository repository;
    private final KpiRepository kpiRepository;
    private final AgentSalaryMapper mapper;
    private final CurrencyRepository currencyRepository;

    @Override
    public AgentSalaryDto create(AgentSalaryCreateDto dto) {
        AgentSalary entity = mapper.toEntity(dto);
        AgentSalary saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public List<AgentSalaryDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AgentSalaryDto getById(UUID agentId) {
        Agent agent = agentRepository.findById(agentId).orElseThrow(() -> new DataNotFoundException("agent is not found"));


        AgentSalary entity = repository.findByAgent(agent)
                .orElseThrow(() -> new RuntimeException("AgentSalary not found"));
        return mapper.toDto(entity);
    }

    @Override
    public AgentSalaryDto update(AgentSalaryDto dto) {
        AgentSalary entity = repository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("AgentSalary not found"));

        mapper.updateEntity(entity, dto);
        AgentSalary updated = repository.save(entity);

        return mapper.toDto(updated);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public void addSalary(TransactionIncomeRequestDTO dto, Agent agent, BigDecimal total) {
        AgentSalary agentSalary = repository.findByAgent(agent)
                .orElseGet(() -> {
                    AgentSalary s = new AgentSalary();
                    s.setAgent(agent);
                    s.setSalary(BigDecimal.ZERO);
                    s.setAddedClients(0);
                    s.setKpiScore(0);
                    s.setTotalTransactionAmount(BigDecimal.ZERO);
                    return s;
                });


        Kpi kpi = kpiRepository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new DataNotFoundException("kpi not found"));

        BigDecimal cacheKpi = new BigDecimal(kpi.getCacheKpi());
        BigDecimal bankKpi = new BigDecimal(kpi.getBankKpi());

        CurrencyEntity currency = currencyRepository.findTopByOrderByCreatedAtDesc()
                .orElseThrow(() -> new DataNotFoundException("currency is not exist yet"));

        BigDecimal currentSalary = agentSalary.getSalary()==null?new BigDecimal("0"):agentSalary.getSalary();

        for (var request : dto.getTypeRequests()) {

            switch (request.getType()) {

                case CLICK, CACHE -> {
                    BigDecimal amount = request.getAmount();

                    if (request.getCurrency().equals(Currency.UZS)) {
                        BigDecimal uzCache = BigDecimal.valueOf(currency.getUzCache());

                        BigDecimal converted = amount.divide(uzCache, 6, RoundingMode.HALF_UP);
                        BigDecimal bonus = converted.multiply(cacheKpi);

                        currentSalary = currentSalary.add(bonus);
                    } else {
                        BigDecimal bonus = amount.multiply(cacheKpi);
                        currentSalary = currentSalary.add(bonus);
                    }
                }

                case BANK -> {
                    BigDecimal uzBank = BigDecimal.valueOf(currency.getUzBank());

                    BigDecimal converted = request.getAmount()
                            .divide(uzBank, 6, RoundingMode.HALF_UP);

                    BigDecimal bonus = converted.multiply(bankKpi);

                    currentSalary = currentSalary.add(bonus);
                }
            }
        }

        agentSalary.setSalary(currentSalary);

        BigDecimal newTotal = agentSalary.getTotalTransactionAmount().add(total);
        agentSalary.setTotalTransactionAmount(newTotal);

        BigDecimal kpiScore = newTotal
                .divide(BigDecimal.valueOf(kpi.getMonthlyTarget()), 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));

        agentSalary.setKpiScore(kpiScore.intValue());

        repository.save(agentSalary);
    }
}