package com.pet.accountsystem.service;

import com.pet.accountsystem.dto.KpiDto;
import com.pet.accountsystem.dto.request.KpiCreateDto;

import java.util.List;
import java.util.UUID;

public interface KpiService {

    KpiDto create(KpiCreateDto dto);

    List<KpiDto> getAll();

    KpiDto getById(UUID id);

    KpiDto update(KpiDto dto);

    void delete(UUID id);
}