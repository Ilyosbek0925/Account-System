package com.pet.accountsystem.service.impl;

import com.pet.accountsystem.dto.request.ClientRequestDTO;
import com.pet.accountsystem.dto.response.ClientResponseDTO;
import com.pet.accountsystem.entity.Base;
import com.pet.accountsystem.entity.Client;
import com.pet.accountsystem.exception.DataNotFoundException;
import com.pet.accountsystem.mapper.ClientMapper;
import com.pet.accountsystem.repository.BazaRepository;
import com.pet.accountsystem.repository.ClientRepository;
import com.pet.accountsystem.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final BazaRepository bazaRepository;
    private final ClientMapper clientMapper;

    @Override
    public ClientResponseDTO createClient(ClientRequestDTO requestDTO) {
        log.info("Creating client with bazaId={}", requestDTO.getBazaId());

        Base base = bazaRepository.findById(requestDTO.getBazaId())
                .orElseThrow(() ->
                        new DataNotFoundException("Baza not found: " + requestDTO.getBazaId()));

        Client client = clientMapper.toEntity(requestDTO, base);
        Client saved = clientRepository.save(client);

        log.info("Client created with id={}", saved.getId());
        return clientMapper.toResponse(saved);
    }

    @Override
    public ClientResponseDTO getById(UUID id) {
        log.info("Fetching client by id={}", id);

        Client client = clientRepository.findById(id)
                .orElseThrow(() ->
                        new DataNotFoundException("Client not found: " + id));

        return clientMapper.toResponse(client);
    }

    @Override
    public List<ClientResponseDTO> getAll() {
        log.info("Fetching all clients");

        return clientRepository.findAll()
                .stream()
                .map(clientMapper::toResponse)
                .toList();
    }

    @Override
    public ClientResponseDTO update(UUID id, ClientRequestDTO requestDTO) {
        log.info("Updating client id={}", id);

        Client client = clientRepository.findById(id)
                .orElseThrow(() ->
                        new DataNotFoundException("Client not found: " + id));

        Base base = bazaRepository.findById(requestDTO.getBazaId())
                .orElseThrow(() ->
                        new DataNotFoundException("Baza not found: " + requestDTO.getBazaId()));

        clientMapper.updateEntity(requestDTO, client, base);
        Client saved = clientRepository.save(client);

        log.info("Client updated id={}", saved.getId());
        return clientMapper.toResponse(saved);
    }

    @Override
    public void deleteById(UUID id) {
        log.info("Deleting client id={}", id);

        Client client = clientRepository.findById(id)
                .orElseThrow(() ->
                        new DataNotFoundException("Client not found: " + id));

        clientRepository.delete(client);

        log.info("Client deleted id={}", id);
    }
}