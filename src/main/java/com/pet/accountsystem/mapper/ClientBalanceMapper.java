package com.pet.accountsystem.mapper;

import com.pet.accountsystem.dto.response.ClientBalanceResponse;
import com.pet.accountsystem.entity.ClientBalance;
import org.springframework.stereotype.Component;

@Component
public class ClientBalanceMapper {

    public ClientBalanceResponse clientBalanceResponse(ClientBalance clientBalance){
        return ClientBalanceResponse.builder()
                .clientId(clientBalance.getId())
                .bankAmount(clientBalance.getBankAmount())
                .clickAmount(clientBalance.getClickAmount())
                .uzsAmount(clientBalance.getUzsAmount())
                .usdAmount(clientBalance.getUsdAmount())
                .total(clientBalance.getTotal())

                .build();
    }

}
