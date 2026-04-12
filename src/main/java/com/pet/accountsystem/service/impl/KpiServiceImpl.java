package com.pet.accountsystem.service.impl;

import com.pet.accountsystem.dto.KpiDto;
import com.pet.accountsystem.dto.request.KpiCreateDto;
import com.pet.accountsystem.entity.Kpi;
import com.pet.accountsystem.mapper.KpiMapper;
import com.pet.accountsystem.repository.KpiRepository;
import com.pet.accountsystem.service.KpiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KpiServiceImpl implements KpiService {

    private final KpiRepository repository;
    private final KpiMapper mapper;

    @Override
    public KpiDto create(KpiCreateDto dto) {
        Kpi entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public List<KpiDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public KpiDto getById(UUID id) {
        Kpi entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kpi not found"));
        return mapper.toDto(entity);
    }

    @Override
    public KpiDto update(KpiDto dto) {
        Kpi entity = repository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Kpi not found"));

        mapper.updateEntity(entity, dto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}