package com.pet.accountsystem.mapper;

import com.pet.accountsystem.dto.request.AgentRequestDTO;
import com.pet.accountsystem.dto.response.AgentResponseDTO;
import com.pet.accountsystem.entity.Agent;
import com.pet.accountsystem.entity.Baza;
import org.springframework.stereotype.Component;

@Component
public class AgentMapper {

    public Agent toEntity(AgentRequestDTO dto, Baza baza) {
        if (dto == null) return null;

        Agent agent = new Agent();
        agent.setFullName(dto.getFullName());
        agent.setPhoneNumber(dto.getPhoneNumber());
        agent.setEmail(dto.getEmail());
        agent.setRole(dto.getRole());
        agent.setActive(dto.isActive());
        agent.setPassword(dto.getPassword());
        agent.setBaza(baza);
        return agent;
    }

    public AgentResponseDTO toResponse(Agent agent) {
        if (agent == null) return null;

        AgentResponseDTO dto = new AgentResponseDTO();
        dto.setId(agent.getId());
        dto.setFullName(agent.getFullName());
        dto.setPhoneNumber(agent.getPhoneNumber());
        dto.setEmail(agent.getEmail());
        dto.setRole(agent.getRole());
        dto.setActive(agent.isActive());
        dto.setBazaId(
                agent.getBaza() != null
                        ? agent.getBaza().getId()
                        : null
        );
        return dto;
    }

    public void updateEntity(AgentRequestDTO dto, Agent agent, Baza baza) {
        if (dto == null || agent == null) return;

        agent.setFullName(dto.getFullName());
        agent.setPhoneNumber(dto.getPhoneNumber());
        agent.setEmail(dto.getEmail());
        agent.setRole(dto.getRole());
        agent.setActive(dto.isActive());
        agent.setPassword(dto.getPassword());
        agent.setBaza(baza);
    }
}