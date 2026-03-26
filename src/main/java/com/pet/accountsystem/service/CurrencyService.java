package com.pet.accountsystem.service;

import com.pet.accountsystem.entity.UnitTransaction;

import java.util.List;

public interface CurrencyService {

    void setUsdAmountToUnitTransactions(List<UnitTransaction> unitTransactions);




}
