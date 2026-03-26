package com.pet.accountsystem.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AgentProfileDTO {
    private UUID agentId;
    private BigDecimal uzdAmount;
    private BigDecimal uzsAmount;
    private BigDecimal clickAmount;
}
