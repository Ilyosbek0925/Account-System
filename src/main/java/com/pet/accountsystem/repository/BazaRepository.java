package com.pet.accountsystem.repository;

import com.pet.accountsystem.entity.Base;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BazaRepository extends JpaRepository<Base, UUID> {
}
