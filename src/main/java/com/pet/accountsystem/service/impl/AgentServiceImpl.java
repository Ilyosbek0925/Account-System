package com.pet.accountsystem.service.impl;

import com.pet.accountsystem.dto.request.AgentRequestDTO;
import com.pet.accountsystem.dto.response.AgentResponseDTO;
import com.pet.accountsystem.entity.Agent;
import com.pet.accountsystem.entity.Baza;
import com.pet.accountsystem.exception.DataNotFoundException;
import com.pet.accountsystem.mapper.AgentMapper;
import com.pet.accountsystem.repository.AgentRepository;
import com.pet.accountsystem.repository.BazaRepository;
import com.pet.accountsystem.service.AgentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AgentServiceImpl implements AgentService {

    private final AgentRepository agentRepository;
    private final BazaRepository bazaRepository;
    private final AgentMapper agentMapper;

    @Override
    public AgentResponseDTO create(AgentRequestDTO requestDTO) {
        log.info("Creating agent with bazaId={}", requestDTO.getBazaId());

        Baza baza = bazaRepository.findById(requestDTO.getBazaId())
                .orElseThrow(() -> new DataNotFoundException("Baza not found: " + requestDTO.getBazaId()));

        Agent agent = agentMapper.toEntity(requestDTO, baza);
        Agent saved = agentRepository.save(agent);

        log.info("Agent created with id={}", saved.getId());
        return agentMapper.toResponse(saved);
    }

    @Override
    public AgentResponseDTO getById(UUID id) {
        log.info("Fetching agent by id={}", id);

        Agent agent = agentRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Agent not found: " + id));

        return agentMapper.toResponse(agent);
    }

    @Override
    public List<AgentResponseDTO> getAll() {
        log.info("Fetching all agents");

        return agentRepository.findAll()
                .stream()
                .map(agentMapper::toResponse)
                .toList();
    }

    @Override
    public AgentResponseDTO update(UUID id, AgentRequestDTO requestDTO) {
        log.info("Updating agent id={}", id);

        Agent agent = agentRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Agent not found: " + id));

        Baza baza = bazaRepository.findById(requestDTO.getBazaId())
                .orElseThrow(() -> new DataNotFoundException("Baza not found: " + requestDTO.getBazaId()));

        agentMapper.updateEntity(requestDTO, agent, baza);
        Agent saved = agentRepository.save(agent);

        log.info("Agent updated id={}", saved.getId());
        return agentMapper.toResponse(saved);
    }

    @Override
    public void deleteById(UUID id) {
        log.info("Deleting agent id={}", id);

        if (!agentRepository.existsById(id)) {
            throw new DataNotFoundException("Agent not found: " + id);
        }

        agentRepository.deleteById(id);
        log.info("Agent deleted id={}", id);
    }
}