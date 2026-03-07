package com.pet.accountsystem.repository;

import com.pet.accountsystem.entity.AgentBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AgentBalanceRepository extends JpaRepository<AgentBalance, UUID> {
}
