package com.pet.accountsystem.service.impl;

import com.pet.accountsystem.dto.AksiyaDto;
import com.pet.accountsystem.dto.request.AksiyaCreateDto;
import com.pet.accountsystem.entity.Aksiya;
import com.pet.accountsystem.mapper.AksiyaMapper;
import com.pet.accountsystem.repository.AksiyaRepository;
import com.pet.accountsystem.service.AksiyaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AksiyaServiceImpl implements AksiyaService {

    private final AksiyaRepository aksiyaRepository;
    private final AksiyaMapper aksiyaMapper;

    public AksiyaDto create(AksiyaCreateDto dto) {
        Aksiya aksiya = aksiyaMapper.toEntity(dto);
        return aksiyaMapper.toDto(aksiyaRepository.save(aksiya));
    }

    public List<AksiyaDto> getAll() {
        List<Aksiya> all = aksiyaRepository.findAll();
        return all.stream().map(aksiyaMapper::toDto).toList();
    }

    public AksiyaDto getById(UUID id) {
        return aksiyaRepository.findById(id)
                .map(aksiyaMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Aksiya not found: " + id));
    }

    public AksiyaDto update(AksiyaDto dto) {
        Aksiya aksiya = aksiyaRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Aksiya not found: " + dto.getId()));
        aksiyaMapper.updateFromDto(dto, aksiya);
        return aksiyaMapper.toDto(aksiyaRepository.save(aksiya));
    }

    public void delete(UUID id) {
        aksiyaRepository.deleteById(id);
    }
}
