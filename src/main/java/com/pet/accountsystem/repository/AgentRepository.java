package com.pet.accountsystem.repository;

import com.pet.accountsystem.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AgentRepository extends JpaRepository<Agent, UUID> {
}
