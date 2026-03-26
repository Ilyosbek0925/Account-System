package com.pet.accountsystem.mapper;

import com.pet.accountsystem.dto.request.BazaRequestDTO;
import com.pet.accountsystem.dto.response.BazaResponseDTO;
import com.pet.accountsystem.entity.Base;
import org.springframework.stereotype.Component;

@Component
public class BazaMapper {

    public Base toEntity(BazaRequestDTO dto) {
        if (dto == null) return null;

        Base base = new Base();
        base.setRegion(dto.getRegion());
        base.setDistrict(dto.getDistrict());
        base.setGroupId(dto.getGroupId());
        return base;
    }

    public BazaResponseDTO toResponse(Base base) {
        if (base == null) return null;

        BazaResponseDTO dto = new BazaResponseDTO();
        dto.setId(base.getId());
        dto.setRegion(base.getRegion());
        dto.setDistrict(base.getDistrict());
        dto.setGroupId(base.getGroupId());
        return dto;
    }

    public void updateEntity(BazaRequestDTO dto, Base base) {
        if (dto == null || base == null) return;

        base.setRegion(dto.getRegion());
        base.setDistrict(dto.getDistrict());
        base.setGroupId(dto.getGroupId());
    }
}