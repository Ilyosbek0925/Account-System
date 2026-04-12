package com.pet.accountsystem.repository;

import com.pet.accountsystem.entity.Aksiya;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AksiyaRepository extends JpaRepository<Aksiya, UUID> {
}
