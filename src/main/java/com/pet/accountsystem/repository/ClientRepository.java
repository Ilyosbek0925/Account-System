package com.pet.accountsystem.repository;

import com.pet.accountsystem.entity.Base;
import com.pet.accountsystem.entity.Client;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    List<Client> findByBase(Base base, Pageable pageable);
}
