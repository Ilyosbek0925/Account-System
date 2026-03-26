package com.pet.accountsystem.mapper;

import com.pet.accountsystem.dto.request.AgentBalanceRequestDTO;
import com.pet.accountsystem.dto.response.AgentBalanceResponseDTO;
import com.pet.accountsystem.entity.Agent;
import com.pet.accountsystem.entity.AgentBalance;
import com.pet.accountsystem.util.FormatUtil;
import org.springframework.stereotype.Component;

import java.text.Normalizer;

@Component
public class AgentBalanceMapper {

    public AgentBalance toEntity(AgentBalanceRequestDTO dto, Agent agent) {
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
                .usdAmount(FormatUtil.toScale(balance.getUsdAmount()))
                .uzsAmount(FormatUtil.toScale(balance.getUzsAmount()))
                .clickAmount(FormatUtil.toScale(balance.getClickAmount()))
                .bankAmount(FormatUtil.toScale(balance.getBankAmount()))
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