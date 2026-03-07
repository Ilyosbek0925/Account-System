package com.pet.accountsystem.repository;

import com.pet.accountsystem.entity.TransactionInkassa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionInkassaRepository extends JpaRepository<TransactionInkassa, UUID> {
}
