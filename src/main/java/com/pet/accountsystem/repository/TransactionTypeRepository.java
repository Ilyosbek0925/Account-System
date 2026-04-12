package com.pet.accountsystem.repository;

import com.pet.accountsystem.entity.TransactionIncome;
import com.pet.accountsystem.entity.UnitTransaction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TransactionTypeRepository extends JpaRepository<UnitTransaction, UUID>, JpaSpecificationExecutor<UnitTransaction> {

    List<UnitTransaction> findByTransactionIncome(TransactionIncome transactionIncome);
}
