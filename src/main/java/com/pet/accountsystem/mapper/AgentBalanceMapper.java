package com.pet.accountsystem.mapper;

import com.pet.accountsystem.dto.request.AgentBalanceRequestDTO;
import com.pet.accountsystem.dto.response.AgentBalanceResponseDTO;
import com.pet.accountsystem.entity.Agent;
import com.pet.accountsystem.entity.AgentBalance;
import org.springframework.stereotype.Component;

@Component
public class AgentBalanceMapper {

    public AgentBalance toEntity(AgentBalanceRequestDTO dto, Agent agent) {
        if (dto == null) return null;

        AgentBalance balance = new AgentBalance();
        balance.setUsdAmount(dto.getUsdAmount());
        balance.setUzsAmount(dto.getUzsAmount());
        balance.setClickAmount(dto.getClickAmount());
        balance.setBankAmount(dto.getBankAmount());
        balance.setAgent(agent);
        return balance;
    }

    public AgentBalanceResponseDTO toResponse(AgentBalance balance) {
        if (balance == null) return null;

        return AgentBalanceResponseDTO.builder()
                .id(balance.getId())
                .usdAmount(balance.getUsdAmount())
                .uzsAmount(balance.getUzsAmount())
                .clickAmount(balance.getClickAmount())
                .bankAmount(balance.getBankAmount())
                .agentId(
                        balance.getAgent() != null
                                ? balance.getAgent().getId()
                                : null
                )
                .build();
    }

    public void updateEntity(AgentBalanceRequestDTO dto,
                             AgentBalance balance,
                             Agent agent) {
        if (dto == null || balance == null) return;

        balance.setUsdAmount(dto.getUsdAmount());
        balance.setUzsAmount(dto.getUzsAmount());
        balance.setClickAmount(dto.getClickAmount());
        balance.setBankAmount(dto.getBankAmount());
        balance.setAgent(agent);
    }
}