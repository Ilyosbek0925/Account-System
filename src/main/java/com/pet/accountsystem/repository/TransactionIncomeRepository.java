package com.pet.accountsystem.repository;

import com.pet.accountsystem.entity.TransactionIncome;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionIncomeRepository extends JpaRepository<TransactionIncome, UUID> {

}
