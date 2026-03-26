package com.pet.accountsystem.service.impl;

import com.pet.accountsystem.dto.request.BazaRequestDTO;
import com.pet.accountsystem.dto.response.BazaResponseDTO;
import com.pet.accountsystem.entity.Base;
import com.pet.accountsystem.exception.DataNotFoundException;
import com.pet.accountsystem.mapper.BazaMapper;
import com.pet.accountsystem.repository.BazaRepository;
import com.pet.accountsystem.service.BazaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BazaServiceImpl implements BazaService {

    private final BazaRepository bazaRepository;
    private final BazaMapper bazaMapper;

    @Override
    public BazaResponseDTO create(BazaRequestDTO dto) {
        log.info("Creating baza");

        Base base = bazaMapper.toEntity(dto);
        Base saved = bazaRepository.save(base);

        log.info("Baza created with id={}", saved.getId());
        return bazaMapper.toResponse(saved);
    }

    @Override
    public BazaResponseDTO getById(UUID id) {
        log.info("Fetching baza by id={}", id);

        Base base = bazaRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Baza not found: " + id));

        return bazaMapper.toResponse(base);
    }

    @Override
    public List<BazaResponseDTO> getAll() {
        log.info("Fetching all bazas");

        return bazaRepository.findAll()
                .stream()
                .map(bazaMapper::toResponse)
                .toList();
    }

    @Override
    public BazaResponseDTO update(UUID id, BazaRequestDTO dto) {
        log.info("Updating baza id={}", id);

        Base base = bazaRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Baza not found: " + id));

        bazaMapper.updateEntity(dto, base);
        Base saved = bazaRepository.save(base);

        log.info("Baza updated id={}", saved.getId());
        return bazaMapper.toResponse(saved);
    }

    @Override
    public void deleteById(UUID id) {
        log.info("Deleting baza id={}", id);

        if (!bazaRepository.existsById(id)) {
            throw new DataNotFoundException("Baza not found: " + id);
        }

        bazaRepository.deleteById(id);
        log.info("Baza deleted id={}", id);
    }
}