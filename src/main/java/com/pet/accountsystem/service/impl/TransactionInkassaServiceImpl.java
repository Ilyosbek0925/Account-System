package com.pet.accountsystem.service.impl;

import com.pet.accountsystem.dto.request.TransactionInkassaRequestDTO;
import com.pet.accountsystem.dto.response.TransactionInkassaResponseDTO;
import com.pet.accountsystem.entity.*;
import com.pet.accountsystem.exception.DataNotFoundException;
import com.pet.accountsystem.exception.NotAcceptableException;
import com.pet.accountsystem.mapper.TransactionInkassaMapper;
import com.pet.accountsystem.projection.TransactionInkassaByAdminProjection;
import com.pet.accountsystem.repository.*;
import com.pet.accountsystem.service.AdminBalanceService;
import com.pet.accountsystem.service.AgentBalanceService;
import com.pet.accountsystem.service.TransactionInkassaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionInkassaServiceImpl implements TransactionInkassaService {

    private final TransactionInkassaRepository transactionInkassaRepository;
    private final AdminRepository adminRepository;
    private final AgentRepository agentRepository;
    private final TransactionInkassaMapper transactionInkassaMapper;
    private final AgentBalanceRepository agentBalanceRepository;
    private final AdminBalanceRepository adminBalanceRepository;
    private final AgentBalanceService agentBalanceService;
    private final AdminBalanceService adminBalanceService;


    @Override
    @Transactional
    public TransactionInkassaResponseDTO create(TransactionInkassaRequestDTO dto) {
        log.info("Creating transaction inkassa adminId={}, agentId={}", dto.getAdminId(), dto.getAgentId());
        AdminBalance adminBalance = null;
        Agent agent = agentRepository.findById(dto.getAgentId())
                .orElseThrow(() -> new DataNotFoundException("Agent not found: " + dto.getAgentId()));


        Admin admin = adminRepository.findById(dto.getAdminId())
                .orElseThrow(() -> new DataNotFoundException("Admin not found: " + dto.getAdminId()));

        AgentBalance agentBalance = agentBalanceRepository.findByAgent(agent).orElseThrow(() -> new DataNotFoundException("agent balance is not exist"));
        Optional<AdminBalance> optionalAdminBalance = adminBalanceRepository.findByAdmin(admin);
        validateAgentBalance(agentBalance, dto);
        adminBalance = optionalAdminBalance.orElseGet(() -> new AdminBalance(new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"), admin));
        agentBalanceService.minusMoney(dto, agentBalance);
        adminBalanceService.addMoney(dto, adminBalance);


        TransactionInkassa inkassa = transactionInkassaMapper.toEntity(dto, admin, agent);
        TransactionInkassa saved = transactionInkassaRepository.save(inkassa);

        log.info("Transaction inkassa created id={}", saved.getId());
        return transactionInkassaMapper.toResponse(saved);
    }

    private void validateAgentBalance(AgentBalance agentBalance, TransactionInkassaRequestDTO dto) {
        if (agentBalance.getUsdAmount().compareTo(dto.getUsdAmount()) < 0 || agentBalance.getUzsAmount().compareTo(dto.getUzsAmount()) < 0 || agentBalance.getClickAmount().compareTo(dto.getClickAmount()) < 0) {
            throw new NotAcceptableException("agent balance is less then required");
        }
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

    @Override
    public List<TransactionInkassaResponseDTO> getTransactionInkasssaByAdminId(UUID adminId, LocalDate fromDate, LocalDate toDate, Pageable pageable) {
        LocalDateTime fromDateTime = null;
        LocalDateTime toDateTime = null;
        if (fromDate != null) {
            fromDateTime
                    = fromDate.atStartOfDay();
        }
        if (toDate != null) {
            toDateTime = toDate.atTime(23, 59, 59);
        }
      Page<TransactionInkassaByAdminProjection> list=transactionInkassaRepository.findByAdmin(adminId,fromDateTime,toDateTime,pageable);
//return list.map(t->transactionInkassaMapper.toResponse(t))
        return null;
    }
}