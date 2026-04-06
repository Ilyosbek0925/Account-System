package com.pet.accountsystem.repository;

import com.pet.accountsystem.entity.Client;
import com.pet.accountsystem.entity.ClientBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface ClientBalanceRepository extends JpaRepository<ClientBalance, UUID> {
    Optional<ClientBalance> findByClient(Client client);

}
