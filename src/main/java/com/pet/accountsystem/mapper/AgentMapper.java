package com.pet.accountsystem.mapper;

import com.pet.accountsystem.dto.request.AgentRequestDTO;
import com.pet.accountsystem.dto.response.AgentResponseDTO;
import com.pet.accountsystem.entity.Agent;
import com.pet.accountsystem.entity.Base;
import org.springframework.stereotype.Component;

@Component
public class AgentMapper {

    public Agent toEntity(AgentRequestDTO dto, Base base) {
        if (dto == null) return null;

        Agent agent = new Agent();
        agent.setFirstName(dto.getFirstName());
        agent.setLastName(dto.getLastName());
        agent.setPhoneNumber(dto.getPhoneNumber());
        agent.setEmail(dto.getEmail());
        agent.setRole(dto.getRole());
        agent.setIsActive(dto.getIsActive());
        agent.setPassword(dto.getPassword());
        agent.setBase(base);
        return agent;
    }

    public AgentResponseDTO toResponse(Agent agent) {
        if (agent == null) return null;

        AgentResponseDTO dto = new AgentResponseDTO();
        dto.setId(agent.getId());
        dto.setFirstName(agent.getFirstName());
        dto.setLastName(agent.getLastName());
        dto.setPhoneNumber(agent.getPhoneNumber());
        dto.setEmail(agent.getEmail());
        dto.setRole(agent.getRole());
        dto.setActive(agent.getIsActive());
        dto.setBazaId(
                agent.getBase() != null
                        ? agent.getBase().getId()
                        : null
        );
        return dto;
    }

    public void updateEntity(AgentRequestDTO dto, Agent agent, Base base) {

        agent.setFirstName(dto.getFirstName());
        agent.setLastName(dto.getLastName());
        agent.setPhoneNumber(dto.getPhoneNumber());
        agent.setEmail(dto.getEmail());
        agent.setRole(dto.getRole());
        agent.setIsActive(dto.getIsActive());
        agent.setPassword(dto.getPassword());
        agent.setBase(base);
    }
}