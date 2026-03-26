package com.pet.accountsystem.specification;

import com.pet.accountsystem.entity.Agent;
import com.pet.accountsystem.entity.UnitTransaction;
import com.pet.accountsystem.entity.enums.TransactionType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class TransactionTypeSpecification {
    public static Specification<UnitTransaction> hasFromDate(LocalDate fromDate) {
        if (fromDate == null) {
            return (root, query, cb) -> cb.conjunction();
        }

        return (root, query, cb) ->
                cb.greaterThanOrEqualTo(
                        root.get("createdAt"),
                        fromDate.atStartOfDay()
                );
    }

    public static Specification<UnitTransaction> hasToDate(LocalDate toDate) {
        if (toDate == null) {
            return (root, query, cb) -> cb.conjunction();
        }

        return (root, query, cb) ->
                cb.lessThanOrEqualTo(
                        root.get("createdAt"),
                        toDate.atTime(23, 59, 59)
                );
    }


    public static Specification<UnitTransaction> hasType(TransactionType type) {
        if (type == null) {
            throw new IllegalStateException("type now cannot be null here in type specification ");
        }

        return (root, query, cb) ->
                cb.equal(
                        root.get("transactionType"),
                        type
                );
    }


    public static Specification<UnitTransaction> hasAgent(Agent agent) {
        if (agent == null) {
            return (root, query, cb) -> cb.conjunction();
        }

        return (root, query, cb) ->
                cb.equal(
                        root.get("transactionIncome").get("agent"),
                        agent
                );
    }




}
