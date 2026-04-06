package com.pet.accountsystem.service.impl;


import com.pet.accountsystem.dto.request.TransactionIncomeRequestDTO;
import com.pet.accountsystem.dto.request.TransactionTypeRequest;
import com.pet.accountsystem.entity.Client;
import com.pet.accountsystem.entity.ClientBalance;
import com.pet.accountsystem.entity.enums.Currency;
import com.pet.accountsystem.repository.ClientBalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientBalanceService {

    private final ClientBalanceRepository clientBalanceRepository;




    public void addMoney(TransactionIncomeRequestDTO dto, Client client) {
        Optional<ClientBalance> byClient = clientBalanceRepository.findByClient(client);
        BigDecimal clickAmount = BigDecimal.ZERO;
        BigDecimal usdAmount = BigDecimal.ZERO;
        BigDecimal uzsAmount = BigDecimal.ZERO;
        BigDecimal bankAmount = BigDecimal.ZERO;

        for (TransactionTypeRequest typeRequest : dto.getTypeRequests()) {
            switch (typeRequest.getType()) {
                case BANK:
                    bankAmount = bankAmount.add(typeRequest.getAmount());
                    break;

                case CLICK:
                    clickAmount = clickAmount.add(typeRequest.getAmount());
                    break;

                case CACHE:
                    if (typeRequest.getCurrency().equals(Currency.UZS)) {
                        uzsAmount = uzsAmount.add(typeRequest.getAmount());
                    } else {
                        usdAmount = usdAmount.add(typeRequest.getAmount());
                    }
                    break;
            }
        }


        if (byClient.isEmpty()) {
            ClientBalance clientBalance = new ClientBalance();
            clientBalance.setUzsAmount(uzsAmount);
            clientBalance.setBankAmount(bankAmount);
            clientBalance.setClickAmount(clickAmount);
            clientBalance.setUsdAmount(usdAmount);
            clientBalance.setClient(client);
            clientBalanceRepository.save(clientBalance);
        } else {
            ClientBalance clientBalance = byClient.get();
            clientBalance.setUsdAmount(clientBalance.getUsdAmount().add(usdAmount));
            clientBalance.setBankAmount(clientBalance.getBankAmount().add(bankAmount));
            clientBalance.setUzsAmount(clientBalance.getUzsAmount().add(uzsAmount));
            clientBalance.setClickAmount(clientBalance.getClickAmount().add(clickAmount));
            clientBalanceRepository.save(clientBalance);
        }

    }
}
