package com.pet.accountsystem.repository;

import com.pet.accountsystem.entity.AdminBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdminBalanceRepository extends JpaRepository<AdminBalance, UUID> {

}
