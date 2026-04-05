package com.pet.accountsystem.repository;

import com.pet.accountsystem.entity.Admin;
import com.pet.accountsystem.entity.AdminBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AdminBalanceRepository extends JpaRepository<AdminBalance, UUID> {

    Optional<AdminBalance> findByAdmin(Admin admin);
}
