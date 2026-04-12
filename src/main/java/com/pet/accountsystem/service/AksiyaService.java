package com.pet.accountsystem.service;

import com.pet.accountsystem.dto.AksiyaDto;
import com.pet.accountsystem.dto.request.AksiyaCreateDto;

import java.util.List;
import java.util.UUID;

public interface AksiyaService {
    AksiyaDto create(AksiyaCreateDto dto);

    List<AksiyaDto> getAll();

    AksiyaDto getById(UUID id);

    AksiyaDto update(AksiyaDto dto);

    void delete(UUID id);
}
