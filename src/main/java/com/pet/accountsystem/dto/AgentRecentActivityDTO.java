package com.pet.accountsystem.dto;

import com.pet.accountsystem.entity.enums.TransactionType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AgentRecentActivityDTO {
    private UUID uuid;
    private String customerFirstName;
    private String customerLastName;
    private LocalDate date;
    private List<TransactionType> types;
    private BigDecimal totalAmount;
}
