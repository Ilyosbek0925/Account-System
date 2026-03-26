package com.pet.accountsystem.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TotalTransactionDTO {

    private BigDecimal amount;
    private Long count;

}