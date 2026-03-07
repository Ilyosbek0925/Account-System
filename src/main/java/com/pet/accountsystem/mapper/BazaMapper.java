package com.pet.accountsystem.mapper;

import com.pet.accountsystem.dto.request.BazaRequestDTO;
import com.pet.accountsystem.dto.response.BazaResponseDTO;
import com.pet.accountsystem.entity.Baza;
import org.springframework.stereotype.Component;

@Component
public class BazaMapper {

    public Baza toEntity(BazaRequestDTO dto) {
        if (dto == null) return null;

        Baza baza = new Baza();
        baza.setRegion(dto.getRegion());
        baza.setDistrict(dto.getDistrict());
        baza.setGroupId(dto.getGroupId());
        return baza;
    }

    public BazaResponseDTO toResponse(Baza baza) {
        if (baza == null) return null;

        BazaResponseDTO dto = new BazaResponseDTO();
        dto.setId(baza.getId());
        dto.setRegion(baza.getRegion());
        dto.setDistrict(baza.getDistrict());
        dto.setGroupId(baza.getGroupId());
        return dto;
    }

    public void updateEntity(BazaRequestDTO dto, Baza baza) {
        if (dto == null || baza == null) return;

        baza.setRegion(dto.getRegion());
        baza.setDistrict(dto.getDistrict());
        baza.setGroupId(dto.getGroupId());
    }
}