package com.pet.accountsystem.repository;

import com.pet.accountsystem.entity.Kpi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface KpiRepository extends JpaRepository<Kpi, UUID> {
}