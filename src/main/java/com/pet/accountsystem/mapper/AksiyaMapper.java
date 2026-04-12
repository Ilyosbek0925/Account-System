package com.pet.accountsystem.mapper;

import com.pet.accountsystem.dto.AksiyaDto;
import com.pet.accountsystem.dto.request.AksiyaCreateDto;
import com.pet.accountsystem.entity.Aksiya;
import org.springframework.stereotype.Component;

@Component
public class AksiyaMapper {

    public Aksiya toEntity(AksiyaCreateDto dto) {
        if (dto == null) return null;

        Aksiya aksiya = new Aksiya();
        aksiya.setName(dto.getName());
        aksiya.setFromDate(dto.getFromDate());
        aksiya.setToDate(dto.getToDate());
        aksiya.setMinAmount(dto.getMinAmount());
        aksiya.setBonus(dto.getBonus());

        return aksiya;
    }

    public AksiyaDto toDto(Aksiya entity) {
        if (entity == null) return null;

        AksiyaDto dto = new AksiyaDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setFromDate(entity.getFromDate());
        dto.setToDate(entity.getToDate());
        dto.setMinAmount(entity.getMinAmount());
        dto.setBonus(entity.getBonus());

        return dto;
    }

    public void updateFromDto(AksiyaDto dto, Aksiya entity) {
        if (dto == null || entity == null) return;

        entity.setName(dto.getName());
        entity.setFromDate(dto.getFromDate());
        entity.setToDate(dto.getToDate());
        entity.setMinAmount(dto.getMinAmount());
        entity.setBonus(dto.getBonus());
    }
}