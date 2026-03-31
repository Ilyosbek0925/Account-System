package com.pet.accountsystem.dto.response;

import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AgentBalanceResponseDTO {
    private String usdAmount;
    private String uzsAmount;
    private String clickAmount;
    private String bankAmount;
}
