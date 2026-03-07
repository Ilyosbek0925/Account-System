package com.pet.accountsystem.service.impl;

import com.pet.accountsystem.dto.request.AgentBalanceRequestDTO;
import com.pet.accountsystem.dto.response.AgentBalanceResponseDTO;
import com.pet.accountsystem.entity.Agent;
import com.pet.accountsystem.entity.AgentBalance;
import com.pet.accountsystem.exception.DataNotFoundException;
import com.pet.accountsystem.mapper.AgentBalanceMapper;
import com.pet.accountsystem.repository.AgentBalanceRepository;
import com.pet.accountsystem.repository.AgentRepository;
import com.pet.accountsystem.service.AgentBalanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public AgentBalanceResponseDTO getById(UUID id) {
        log.info("Fetching agent balance by id={}", id);

        AgentBalance balance = agentBalanceRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("AgentBalance not found: " + id));

        return agentBalanceMapper.toResponse(balance);
    }

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
}