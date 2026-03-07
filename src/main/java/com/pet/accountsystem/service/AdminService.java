package com.pet.accountsystem.service;

import com.pet.accountsystem.dto.request.AdminRequestDTO;
import com.pet.accountsystem.dto.response.AdminResponseDTO;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public interface AdminService {
     AdminResponseDTO create(AdminRequestDTO dto);

     AdminResponseDTO getById(UUID id);

     List<AdminResponseDTO> getAll();

     AdminResponseDTO update(UUID id, AdminRequestDTO dto);

    void deleteById(UUID id);
}
