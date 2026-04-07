package com.pet.accountsystem.dto.response;


import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionInkassaResponseDTO {
    private UUID id;

    private LocalDateTime transactionTime;

    private UUID adminId;
    private String agentFirstName;
    private String agentLastName;
    private String agentPhoneNumber;

    private UUID agentId;

    private String usdAmount;
    private String uzsAmount;
    private String clickAmount;
    private String bankAmount;

    private String description;
}
