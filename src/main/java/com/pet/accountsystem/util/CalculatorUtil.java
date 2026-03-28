package com.pet.accountsystem.util;

import com.pet.accountsystem.entity.UnitTransaction;

import java.math.BigDecimal;
import java.util.List;

public final class CalculatorUtil {

    private CalculatorUtil() {
    }

    public static BigDecimal setTransactionIncomeTotalAmount(List<UnitTransaction> unitTransactions) {
        return unitTransactions.stream()
                .map(UnitTransaction::getUsdAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
