package com.pet.accountsystem.repository;

import com.pet.accountsystem.dto.TotalTransactionDTO;
import com.pet.accountsystem.entity.Agent;
import com.pet.accountsystem.entity.TransactionIncome;
import com.pet.accountsystem.entity.enums.TransactionType;
import com.pet.accountsystem.projection.TransactionReportProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TransactionIncomeRepository extends JpaRepository<TransactionIncome, UUID>, JpaSpecificationExecutor<TransactionIncome> {

    Page<TransactionIncome> findAllByAgent(Agent agent, Specification<TransactionIncome> specification, Pageable pageable);


    @Query("""
       select distinct ti
       from TransactionIncome ti
       left join fetch ti.types
       where ti.id in :ids
       """)
    List<TransactionIncome> findAllWithTypesByIds(List<UUID> ids);


    @Query("""
       select new com.pet.accountsystem.dto.TotalTransactionDTO(
            SUM(t.total),
            COUNT(t)
       )
       from TransactionIncome t
       where t.agent.id = :agentId
       """)
    TotalTransactionDTO getTotalTransaction(@Param("agentId") UUID agentId);



    @Query(value = """
            select
                c.first_name as firstName,
                c.last_name as lastName,
                ti.created_at as transactionDate,
                ut.usd_amount as usdAmount
            from transaction_income ti
            join clients c on c.id = ti.client_id
            join agents ag on ag.id = ti.agent_id
            join unit_transactions ut on ut.transaction_income_id = ti.id
            where (ti.agent_id=:agentId) and
                            (:transactionType is null or ut.transaction_type = :transactionType)
              and (:fromDate is null or ti.created_at >= :fromDate)
              and (:toDate is null or ti.created_at <= :toDate)
            order by ti.created_at desc
            """,
            countQuery = """
            select count(*)
            from transaction_income ti
            join client c on c.id = ti.client_id
            join unit_transactions ut on ut.transaction_income_id = ti.id
            where (:transactionType is null or ut.transaction_type = :transactionType)
              and (:fromDate is null or ti.created_at >= :fromDate)
              and (:toDate is null or ti.created_at <= :toDate)
            """,
            nativeQuery = true)
    Page<TransactionReportProjection> findTransactions(
            @Param(("agentId")) UUID agentId,
            @Param("transactionType") TransactionType transactionType,
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate,
            Pageable pageable
    );
}