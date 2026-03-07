package com.pet.accountsystem.service.impl;

import com.pet.accountsystem.dto.request.TransactionInkassaRequestDTO;
import com.pet.accountsystem.dto.response.TransactionInkassaResponseDTO;
import com.pet.accountsystem.entity.Admin;
import com.pet.accountsystem.entity.Agent;
import com.pet.accountsystem.entity.TransactionInkassa;
import com.pet.accountsystem.exception.DataNotFoundException;
import com.pet.accountsystem.mapper.TransactionInkassaMapper;
import com.pet.accountsystem.repository.AdminRepository;
import com.pet.accountsystem.repository.AgentRepository;
import com.pet.accountsystem.repository.TransactionInkassaRepository;
import com.pet.accountsystem.service.TransactionInkassaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionInkassaServiceImpl implements TransactionInkassaService {

    private final TransactionInkassaRepository transactionInkassaRepository;
    private final AdminRepository adminRepository;
    private final AgentRepository agentRepository;
    private final TransactionInkassaMapper transactionInkassaMapper;

    @Override
    public TransactionInkassaResponseDTO create(TransactionInkassaRequestDTO dto) {
        log.info("Creating transaction inkassa adminId={}, agentId={}", dto.getAdminId(), dto.getAgentId());

        Admin admin = adminRepository.findById(dto.getAdminId())
                .orElseThrow(() -> new DataNotFoundException("Admin not found: " + dto.getAdminId()));

        Agent agent = agentRepository.findById(dto.getAgentId())
                .orElseThrow(() -> new DataNotFoundException("Agent not found: " + dto.getAgentId()));

        TransactionInkassa inkassa = transactionInkassaMapper.toEntity(dto, admin, agent);
        TransactionInkassa saved = transactionInkassaRepository.save(inkassa);

        log.info("Transaction inkassa created id={}", saved.getId());
        return transactionInkassaMapper.toResponse(saved);
    }

    @Override
    public TransactionInkassaResponseDTO getById(UUID id) {
        log.info("Fetching transaction inkassa by id={}", id);

        TransactionInkassa inkassa = transactionInkassaRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("TransactionInkassa not found: " + id));

        return transactionInkassaMapper.toResponse(inkassa);
    }

    @Override
    public List<TransactionInkassaResponseDTO> getAll() {
        log.info("Fetching all transaction inkassa records");

        return transactionInkassaRepository.findAll()
                .stream()
                .map(transactionInkassaMapper::toResponse)
                .toList();
    }

    @Override
    public TransactionInkassaResponseDTO update(UUID id, TransactionInkassaRequestDTO dto) {
        log.info("Updating transaction inkassa id={}", id);

        TransactionInkassa inkassa = transactionInkassaRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("TransactionInkassa not found: " + id));

        Admin admin = adminRepository.findById(dto.getAdminId())
                .orElseThrow(() -> new DataNotFoundException("Admin not found: " + dto.getAdminId()));

        Agent agent = agentRepository.findById(dto.getAgentId())
                .orElseThrow(() -> new DataNotFoundException("Agent not found: " + dto.getAgentId()));

        transactionInkassaMapper.updateEntity(dto, inkassa, admin, agent);
        TransactionInkassa saved = transactionInkassaRepository.save(inkassa);

        log.info("Transaction inkassa updated id={}", saved.getId());
        return transactionInkassaMapper.toResponse(saved);
    }

    @Override
    public void deleteById(UUID id) {
        log.info("Deleting transaction inkassa id={}", id);

        TransactionInkassa inkassa = transactionInkassaRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("TransactionInkassa not found: " + id));

        transactionInkassaRepository.delete(inkassa);

        log.info("Transaction inkassa deleted id={}", id);
    }
}