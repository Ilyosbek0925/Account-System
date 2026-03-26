package com.pet.accountsystem.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionIncomeRequestDTO {
    @NotNull
    private UUID agentId;
    @NotNull
    private UUID clientId;
    @NotNull
    List<TransactionTypeRequest> typeRequests;
    @NotNull
    private String description;
}
