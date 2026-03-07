package com.pet.accountsystem.service.impl;

import com.pet.accountsystem.dto.request.AdminRequestDTO;
import com.pet.accountsystem.dto.response.AdminResponseDTO;
import com.pet.accountsystem.entity.Admin;
import com.pet.accountsystem.exception.DataNotFoundException;
import com.pet.accountsystem.mapper.AdminMapper;
import com.pet.accountsystem.repository.AdminRepository;
import com.pet.accountsystem.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    @Override
    public AdminResponseDTO create(AdminRequestDTO dto) {
        Admin admin = adminMapper.toEntity(dto);
        Admin saved = adminRepository.save(admin);
        return adminMapper.toResponse(saved);
    }

    @Override
    public AdminResponseDTO getById(UUID id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Admin not found: " + id));
        return adminMapper.toResponse(admin);
    }

    @Override
    public List<AdminResponseDTO> getAll() {
        return adminRepository.findAll()
                .stream()
                .map(adminMapper::toResponse)
                .toList();
    }

    @Override
    public AdminResponseDTO update(UUID id, AdminRequestDTO dto) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Admin not found: " + id));

        adminMapper.updateEntity(dto, admin);

        Admin saved = adminRepository.save(admin);
        return adminMapper.toResponse(saved);
    }

    @Override
    public void deleteById(UUID id) {
        if (!adminRepository.existsById(id)) {
            throw new DataNotFoundException("Admin not found: " + id);
        }
        adminRepository.deleteById(id);
    }
}