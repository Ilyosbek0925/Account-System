package com.pet.accountsystem.mapper;

import com.pet.accountsystem.dto.request.TransactionInkassaRequestDTO;
import com.pet.accountsystem.dto.response.TransactionInkassaResponseDTO;
import com.pet.accountsystem.entity.Admin;
import com.pet.accountsystem.entity.Agent;
import com.pet.accountsystem.entity.TransactionInkassa;
import org.springframework.stereotype.Component;

@Component
public class TransactionInkassaMapper {

    public TransactionInkassa toEntity(TransactionInkassaRequestDTO dto,
                                       Admin admin,
                                       Agent agent) {
        if (dto == null) return null;

        TransactionInkassa inkassa = new TransactionInkassa();
        inkassa.setAdmin(admin);
        inkassa.setAgent(agent);
        inkassa.setUsdAmount(dto.getUsdAmount());
        inkassa.setUzsAmount(dto.getUzsAmount());
        inkassa.setClickAmount(dto.getClickAmount());
        inkassa.setBankAmount(dto.getBankAmount());
        inkassa.setDescription(dto.getDescription());
        return inkassa;
    }

    public TransactionInkassaResponseDTO toResponse(TransactionInkassa inkassa) {
        if (inkassa == null) return null;

        TransactionInkassaResponseDTO dto = new TransactionInkassaResponseDTO();
        dto.setId(inkassa.getId());
        dto.setAdminId(
                inkassa.getAdmin() != null
                        ? inkassa.getAdmin().getId()
                        : null
        );
        dto.setAgentId(
                inkassa.getAgent() != null
                        ? inkassa.getAgent().getId()
                        : null
        );
//        dto.setUsdAmount(inkassa.getUsdAmount());
//        dto.setUzsAmount(inkassa.getUzsAmount());
//        dto.setClickAmount(inkassa.getClickAmount());
//        dto.setBankAmount(inkassa.getBankAmount());
        dto.setDescription(inkassa.getDescription());
        return dto;
    }

    public void updateEntity(TransactionInkassaRequestDTO dto,
                             TransactionInkassa inkassa,
                             Admin admin,
                             Agent agent) {
        if (dto == null || inkassa == null) return;

        inkassa.setAdmin(admin);
        inkassa.setAgent(agent);
        inkassa.setUsdAmount(dto.getUsdAmount());
        inkassa.setUzsAmount(dto.getUzsAmount());
        inkassa.setClickAmount(dto.getClickAmount());
        inkassa.setBankAmount(dto.getBankAmount());
        inkassa.setDescription(dto.getDescription());
    }
}