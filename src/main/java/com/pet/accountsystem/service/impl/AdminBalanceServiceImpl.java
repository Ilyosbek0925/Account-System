package com.pet.accountsystem.service.impl;

import com.pet.accountsystem.dto.request.AdminBalanceRequestDTO;
import com.pet.accountsystem.dto.response.AdminBalanceResponseDTO;
import com.pet.accountsystem.entity.Admin;
import com.pet.accountsystem.entity.AdminBalance;
import com.pet.accountsystem.exception.DataNotFoundException;
import com.pet.accountsystem.mapper.AdminBalanceMapper;
import com.pet.accountsystem.repository.AdminBalanceRepository;
import com.pet.accountsystem.repository.AdminRepository;
import com.pet.accountsystem.service.AdminBalanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminBalanceServiceImpl implements AdminBalanceService {

    private final AdminBalanceRepository adminBalanceRepository;
    private final AdminRepository adminRepository;
    private final AdminBalanceMapper adminBalanceMapper;

    @Override
    public AdminBalanceResponseDTO create(AdminBalanceRequestDTO dto) {
        log.info("Creating admin balance for adminId={}", dto.getAdminId());

        Admin admin = adminRepository.findById(dto.getAdminId())
                .orElseThrow(() -> new DataNotFoundException("Admin not found: " + dto.getAdminId()));

        AdminBalance balance = adminBalanceMapper.toEntity(dto, admin);
        AdminBalance saved = adminBalanceRepository.save(balance);

        log.info("Admin balance created with id={}", saved.getId());
        return adminBalanceMapper.toResponse(saved);
    }

    @Override
    public AdminBalanceResponseDTO getById(UUID id) {
        log.info("Fetching admin balance by id={}", id);

        AdminBalance balance = adminBalanceRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("AdminBalance not found: " + id));

        return adminBalanceMapper.toResponse(balance);
    }

    @Override
    public List<AdminBalanceResponseDTO> getAll() {
        log.info("Fetching all admin balances");

        return adminBalanceRepository.findAll()
                .stream()
                .map(adminBalanceMapper::toResponse)
                .toList();
    }

    @Override
    public AdminBalanceResponseDTO update(UUID id, AdminBalanceRequestDTO dto) {

        AdminBalance balance = adminBalanceRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("AdminBalance not found: " + id));

        Admin admin = adminRepository.findById(dto.getAdminId())
                .orElseThrow(() -> new DataNotFoundException("Admin not found: " + dto.getAdminId()));

        adminBalanceMapper.updateEntity(dto, balance);
        balance.setAdmin(admin);

        AdminBalance saved = adminBalanceRepository.save(balance);

        return adminBalanceMapper.toResponse(saved);
    }

    @Override
    public void deleteById(UUID id) {
        log.info("Deleting admin balance id={}", id);

        if (!adminBalanceRepository.existsById(id)) {
            throw new DataNotFoundException("AdminBalance not found: " + id);
        }

        adminBalanceRepository.deleteById(id);
        log.info("Admin balance deleted id={}", id);
    }
}