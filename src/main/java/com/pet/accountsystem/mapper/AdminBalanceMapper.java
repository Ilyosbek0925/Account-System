package com.pet.accountsystem.mapper;

import com.pet.accountsystem.dto.request.AdminBalanceRequestDTO;
import com.pet.accountsystem.dto.request.AgentBalanceRequestDTO;
import com.pet.accountsystem.dto.response.AdminBalanceResponseDTO;
import com.pet.accountsystem.entity.Admin;
import com.pet.accountsystem.entity.AdminBalance;
import org.springframework.stereotype.Component;

@Component
public class AdminBalanceMapper {

    public AdminBalance toEntity(AdminBalanceRequestDTO dto, Admin admin) {
        if (dto == null) return null;

        AdminBalance balance = new AdminBalance();
        balance.setUsdAmount(dto.getUsdAmount());
        balance.setUzsAmount(dto.getUzsAmount());
        balance.setClickAmount(dto.getClickAmount());
        balance.setBankAmount(dto.getBankAmount());
        balance.setAdmin(admin);
        return balance;
    }

    public AdminBalanceResponseDTO toResponse(AdminBalance balance) {
        if (balance == null) return null;

        return AdminBalanceResponseDTO.builder()
                .id(balance.getId())
                .usdAmount(balance.getUsdAmount())
                .uzsAmount(balance.getUzsAmount())
                .clickAmount(balance.getClickAmount())
                .bankAmount(balance.getBankAmount())
                .adminId(
                        balance.getAdmin() != null
                                ? balance.getAdmin().getId()
                                : null
                )
                .build();
    }

    public void updateEntity(AdminBalanceRequestDTO dto, AdminBalance balance) {
        if (dto == null || balance == null) return;

        balance.setUsdAmount(dto.getUsdAmount());
        balance.setUzsAmount(dto.getUzsAmount());
        balance.setClickAmount(dto.getClickAmount());
        balance.setBankAmount(dto.getBankAmount());
    }
}