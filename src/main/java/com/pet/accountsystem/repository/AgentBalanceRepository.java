package com.pet.accountsystem.repository;

import com.pet.accountsystem.entity.Agent;
import com.pet.accountsystem.entity.AgentBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AgentBalanceRepository extends JpaRepository<AgentBalance, UUID> {
    Optional<AgentBalance> findByAgent(Agent agent);
}
