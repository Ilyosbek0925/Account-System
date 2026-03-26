package com.pet.accountsystem.specification;

import com.pet.accountsystem.entity.TransactionIncome;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class TransactionIncomeSpecification {
    public static Specification<TransactionIncome> hasFromDate(LocalDate fromDate) {
        if (fromDate == null) {
            return (root, query, cb) -> cb.conjunction();
        }

        return (root, query, cb) ->
                cb.greaterThanOrEqualTo(
                        root.get("createdAt"),
                        fromDate.atStartOfDay()
                );
    }

    public static Specification<TransactionIncome> hasToDate(LocalDate toDate) {
        if (toDate == null) {
            return (root, query, cb) -> cb.conjunction();
        }

        return (root, query, cb) ->
                cb.lessThanOrEqualTo(
                        root.get("createdAt"),
                        toDate.atTime(23, 59, 59)
                );
    }

}
