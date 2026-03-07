package com.pet.accountsystem.mapper;

import com.pet.accountsystem.dto.request.ClientRequestDTO;
import com.pet.accountsystem.dto.response.ClientResponseDTO;
import com.pet.accountsystem.entity.Baza;
import com.pet.accountsystem.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Client toEntity(ClientRequestDTO dto, Baza baza) {
        if (dto == null) return null;

        Client client = new Client();
        client.setStatus(dto.getStatus());
        client.setGroupId(dto.getGroupId());
        client.setClientType(dto.getClientType());
        client.setBaza(baza);
        return client;
    }

    public ClientResponseDTO toResponse(Client client) {
        if (client == null) return null;

        ClientResponseDTO dto = new ClientResponseDTO();
        dto.setId(client.getId());
        dto.setStatus(client.getStatus());
        dto.setGroupId(client.getGroupId());
        dto.setClientType(client.getClientType());
        dto.setBazaId(
                client.getBaza() != null
                        ? client.getBaza().getId()
                        : null
        );
        return dto;
    }

    public void updateEntity(ClientRequestDTO dto, Client client, Baza baza) {
        if (dto == null || client == null) return;

        client.setStatus(dto.getStatus());
        client.setGroupId(dto.getGroupId());
        client.setClientType(dto.getClientType());
        client.setBaza(baza);
    }
}