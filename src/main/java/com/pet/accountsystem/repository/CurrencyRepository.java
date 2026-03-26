package com.pet.accountsystem.repository;

import com.pet.accountsystem.entity.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, UUID> {
    Optional<CurrencyEntity> findTopByOrderByCreatedAtDesc();
}
