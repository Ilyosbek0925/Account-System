package com.pet.accountsystem.mapper;

import com.pet.accountsystem.dto.request.ClientRequestDTO;
import com.pet.accountsystem.dto.response.ClientResponseDTO;
import com.pet.accountsystem.entity.Base;
import com.pet.accountsystem.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Client toEntity(ClientRequestDTO dto, Base base) {

        Client client = new Client();
        client.setFirstName(dto.getFirstName());
        client.setLastName(dto.getLastName());
        client.setPhoneNumber(dto.getPhoneNumber());
        client.setGroupId(dto.getGroupId());
        client.setClientType(dto.getClientType().toString());
        client.setBase(base);
        return client;
    }

    public ClientResponseDTO toResponse(Client client) {

        ClientResponseDTO dto = new ClientResponseDTO();
        dto.setFirstName(client.getFirstName());
        dto.setLastName(client.getLastName());
        dto.setId(client.getId());
        dto.setStatus(client.getStatus());
        dto.setPhoneNumber(client.getPhoneNumber());

        dto.setGroupId(client.getGroupId());
        dto.setClientType(client.getClientType());
        dto.setBazaId(
                client.getBase() != null
                        ? client.getBase().getId()
                        : null
        );
        return dto;
    }

    public void updateEntity(ClientRequestDTO dto, Client client, Base base) {
        client.setPhoneNumber(dto.getPhoneNumber());
        client.setFirstName(dto.getFirstName());
        client.setLastName(dto.getLastName());
        client.setGroupId(dto.getGroupId());
        client.setClientType(dto.getClientType().toString());
        client.setBase(base);
    }
}