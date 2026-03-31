package com.pet.accountsystem.service.impl;

import com.pet.accountsystem.entity.CurrencyEntity;
import com.pet.accountsystem.entity.UnitTransaction;
import com.pet.accountsystem.entity.enums.Currency;
import com.pet.accountsystem.exception.DataNotFoundException;
import com.pet.accountsystem.repository.CurrencyRepository;
import com.pet.accountsystem.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Override
    public void setUsdAmountToUnitTransactions(List<UnitTransaction> unitTransactions) {
        CurrencyEntity dailyCurrency = getDailyCurrency();
        unitTransactions.forEach(unitTransaction -> {
            BigDecimal amount = unitTransaction.getAmount();
            switch (unitTransaction.getTransactionType()) {

                case BANK -> {
                    if (unitTransaction.getCurrency().equals(Currency.USD)) {
                        unitTransaction.setUsdAmount(amount);
                    } else {
                        unitTransaction.setUsdAmount(
                                amount.divide(BigDecimal.valueOf(dailyCurrency.getUzBank()), 6, RoundingMode.HALF_UP)
                        );
                    }
                }

                case CACHE -> {
                    if (unitTransaction.getCurrency().equals(Currency.USD)) {
                        unitTransaction.setUsdAmount(amount);
                    } else {
                        unitTransaction.setUsdAmount(
                                amount.divide(BigDecimal.valueOf(dailyCurrency.getUzCache()), 6, RoundingMode.HALF_UP)
                        );
                    }
                }

                case CLICK -> {
                    if (unitTransaction.getCurrency().equals(Currency.USD)) {
                        unitTransaction.setUsdAmount(amount);
                    } else {
                        unitTransaction.setUsdAmount(
                                amount.divide(BigDecimal.valueOf(dailyCurrency.getUzClick()), 6, RoundingMode.HALF_UP)
                        );
                    }
                }

//                case TERMINAL -> {
//                    if (unitTransaction.getCurrency().equals(Currency.USD)) {
//                        unitTransaction.setUsdAmount(amount);
//                    } else {
//                        unitTransaction.setUsdAmount(
//                                amount.divide(BigDecimal.valueOf(dailyCurrency.getUzTerminal()), 6, RoundingMode.HALF_UP)
//                        );
//                    }
//                }
            }
        });


    }


    private CurrencyEntity getDailyCurrency() {
        return currencyRepository.findTopByOrderByCreatedAtDesc().orElseThrow(() -> new DataNotFoundException("currency is not exist yet"));
    }


}
