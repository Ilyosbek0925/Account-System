package com.pet.accountsystem.mapper;

import com.pet.accountsystem.dto.request.AdminRequestDTO;
import com.pet.accountsystem.dto.response.AdminResponseDTO;
import com.pet.accountsystem.entity.Admin;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    public Admin toEntity(AdminRequestDTO dto) {
        if (dto == null) return null;

        Admin admin = new Admin();
        admin.setFullName(dto.getFullName());
        admin.setPhoneNumber(dto.getPhoneNumber());
        admin.setEmail(dto.getEmail());
        admin.setRole(dto.getRole());
        admin.setActive(dto.isActive());
        admin.setPassword(dto.getPassword());
        return admin;
    }

    public AdminResponseDTO toResponse(Admin admin) {
        if (admin == null) return null;

        AdminResponseDTO dto = new AdminResponseDTO();
        dto.setId(admin.getId());
        dto.setFullName(admin.getFullName());
        dto.setPhoneNumber(admin.getPhoneNumber());
        dto.setEmail(admin.getEmail());
        dto.setRole(admin.getRole());
        dto.setActive(admin.isActive());
        dto.setPassword(admin.getPassword());
        return dto;
    }

    public void updateEntity(AdminRequestDTO dto, Admin admin) {
        if (dto == null || admin == null) return;

        admin.setFullName(dto.getFullName());
        admin.setPhoneNumber(dto.getPhoneNumber());
        admin.setEmail(dto.getEmail());
        admin.setRole(dto.getRole());
        admin.setActive(dto.isActive());
        admin.setPassword(dto.getPassword());
    }
}