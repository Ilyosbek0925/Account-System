package com.pet.accountsystem.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pet.accountsystem.entity.enums.TransactionType;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class TransactionIncomeResponse {
    private UUID id;
    private UUID agentId;
    private UUID clientId;
    private String firstName;
    private String lastName;

    private List<TransactionType> types;

    private String totalAmount;

    private String description;
}
