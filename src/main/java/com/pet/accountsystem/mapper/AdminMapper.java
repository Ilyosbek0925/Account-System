package com.pet.accountsystem.mapper;

import com.pet.accountsystem.dto.request.AdminRequestDTO;
import com.pet.accountsystem.dto.response.AdminResponseDTO;
import com.pet.accountsystem.entity.Admin;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    public Admin toEntity(AdminRequestDTO dto) {
        Admin admin = new Admin();
        fillCommonFields(dto, admin);
        return admin;
    }

    private void fillCommonFields(AdminRequestDTO dto, Admin admin) {
        admin.setFirstName(dto.getFirstName());
        admin.setLastName(dto.getLastName());
        admin.setPhoneNumber(dto.getPhoneNumber());
        admin.setEmail(dto.getEmail());
        admin.setRole(dto.getRole());
        admin.setIsActive(dto.isActive());
        admin.setPassword(dto.getPassword());
    }

    public AdminResponseDTO toResponse(Admin admin) {
        if (admin == null) return null;

        AdminResponseDTO dto = new AdminResponseDTO();
        dto.setId(admin.getId());
        dto.setFirstName(admin.getFirstName());
        dto.setLastName(admin.getLastName());
        dto.setPhoneNumber(admin.getPhoneNumber());
        dto.setEmail(admin.getEmail());
        dto.setRole(admin.getRole());
        dto.setActive(admin.getIsActive());
        dto.setPassword(admin.getPassword());
        return dto;
    }

    public void updateEntity(AdminRequestDTO dto, Admin admin) {
        if (dto == null || admin == null) return;

        fillCommonFields(dto, admin);
    }
}