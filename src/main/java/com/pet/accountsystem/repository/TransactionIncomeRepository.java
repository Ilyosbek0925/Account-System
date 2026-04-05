package com.pet.accountsystem.repository;

import com.pet.accountsystem.dto.TotalTransactionDTO;
import com.pet.accountsystem.entity.Agent;
import com.pet.accountsystem.entity.TransactionIncome;
import com.pet.accountsystem.entity.UnitTransaction;
import com.pet.accountsystem.projection.TransactionAllTypeProjection;
import com.pet.accountsystem.projection.TransactionReportProjection;
import com.pet.accountsystem.projection.TransactionTypeSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
                ti.id as Id,
                c.first_name as firstName,
                c.last_name as lastName,
                ti.created_at as transactionDate,
                ut.usd_amount as usdAmount
            from transaction_income ti
            join clients c on c.id = ti.client_id
            join unit_transactions ut on ut.transaction_income_id = ti.id
            where ti.agent_id = :agentId
              and (:transactionType is null or ut.transaction_type = cast(:transactionType as varchar))
              and (cast(:fromDate as timestamp) is null or ti.created_at >= cast(:fromDate as timestamp))
              and (cast(:toDate as timestamp) is null or ti.created_at < cast(:toDate as timestamp))
            order by ti.created_at desc
            """,
                countQuery = """
            select count(*)
            from transaction_income ti
            join clients c on c.id = ti.client_id
            join unit_transactions ut on ut.transaction_income_id = ti.id
            where ti.agent_id = :agentId
              and (:transactionType is null or ut.transaction_type = cast(:transactionType as varchar))
              and (cast(:fromDate as timestamp) is null or ti.created_at >= cast(:fromDate as timestamp))
              and (cast(:toDate as timestamp) is null or ti.created_at < cast(:toDate as timestamp))
            """,
                nativeQuery = true)
        Page<TransactionReportProjection> findTransactions(
                @Param("agentId") UUID agentId,
                @Param("transactionType") String transactionType,
                @Param("fromDate") LocalDateTime fromDate,
                @Param("toDate") LocalDateTime toDate,
                Pageable pageable
        );




    @Query(value = """
        select
            c.id as id,
            c.first_name as firstName,
            c.last_name as lastName,
            ti.created_at as transactionDate,
            ti.total as usdAmount,
            string_agg(distinct cast(ut.transaction_type as varchar), ',') as types
        from transaction_income ti
        join clients c on c.id = ti.client_id
        join unit_transactions ut on ut.transaction_income_id = ti.id
        where ti.agent_id = :agentId
          and (cast(:fromDateTime as timestamp) is null or ti.created_at >= cast(:fromDateTime as timestamp))
          and (cast(:toDateTime as timestamp) is null or ti.created_at < cast(:toDateTime as timestamp))
        group by ti.id, c.first_name, c.last_name, ti.created_at, ti.total
        order by ti.created_at desc
        """,
            countQuery = """
        select count(*)
        from (
            select ti.id
            from transaction_income ti
            join clients c on c.id = ti.client_id
            join unit_transactions ut on ut.transaction_income_id = ti.id
            where ti.agent_id = :agentId
              and (cast(:fromDateTime as timestamp) is null or ti.created_at >= cast(:fromDateTime as timestamp))
              and (cast(:toDateTime as timestamp) is null or ti.created_at < cast(:toDateTime as timestamp))
            group by ti.id
        ) t
        """,
            nativeQuery = true)
    Page<TransactionAllTypeProjection> findTransactionsByAllTypes(
            @Param("agentId") UUID agentId,
            @Param("fromDateTime") LocalDateTime fromDateTime,
            @Param("toDateTime") LocalDateTime toDateTime,
            Pageable pageable
    );



    @Query(value = """
        select
            u.transaction_type as transactionType,
            sum(u.amount) as totalAmount,
            sum(u.usd_amount) as totalUsdAmount
        from unit_transactions u
        join transaction_income t on t.id = u.transaction_income_id
        where t.agent_id = :agentId
        group by u.transaction_type
        order by u.transaction_type
        """, nativeQuery = true)
    List<TransactionTypeSummary> sumTransactionsByTypes(@Param("agentId") UUID agentId);

    @Query("SELECT u FROM UnitTransaction u WHERE u.transactionIncome.id = :transactionIncomeId")
    List<UnitTransaction> findByTransactionIncomeId(@Param("transactionIncomeId") UUID transactionIncomeId);
}
