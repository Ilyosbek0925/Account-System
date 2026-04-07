package com.pet.accountsystem.repository;

import com.pet.accountsystem.entity.TransactionInkassa;
import com.pet.accountsystem.projection.TransactionInkassaByAdminProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.UUID;

public interface TransactionInkassaRepository extends JpaRepository<TransactionInkassa, UUID> {


    @Query("""
        select 
            t.id as id,
            t.createdAt as transactionTime,
            a.firstName as agentFirstName,
            a.lastName as agentLastName,
            a.phoneNumber as agentPhoneNumber,
            a.id as agentId,
            t.usdAmount as usdAmount,
            t.uzsAmount as uzsAmount,
            t.clickAmount as clickAmount,
            t.bankAmount as bankAmount,
            t.description as description
        from TransactionInkassa t
        join t.agent a
        join t.admin ad
        where ad.id = :adminId
            and (cast(:fromDate as timestamp) is null or t.createdAt >= cast(:fromDate as timestamp))
              and (cast(:toDate as timestamp) is null or t.createdAt < cast(:toDate as timestamp))
    """)
    Page<TransactionInkassaByAdminProjection> findByAdmin(
            @Param("adminId") UUID adminId,
            @Param("fromDate") LocalDateTime fromDate,
            @Param("toDate") LocalDateTime toDate,
            Pageable pageable
    );
}
