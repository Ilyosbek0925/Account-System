package com.pet.accountsystem.dto.request;


import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AgentBalanceRequestDTO {

    private String usdAmount;
    private String uzsAmount;
    private String clickAmount;
    private String bankAmount;
    private UUID agentId;

}
