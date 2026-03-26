package com.pet.accountsystem.controller;

import com.pet.accountsystem.dto.TotalTransactionDTO;
import com.pet.accountsystem.dto.request.TransactionIncomeRequestDTO;
import com.pet.accountsystem.dto.response.TransactionIncomeResponse;
import com.pet.accountsystem.entity.enums.TransactionType;
import com.pet.accountsystem.service.TransactionIncomeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/transaction-income")
public class TransactionIncomeController {

    private final TransactionIncomeService transactionIncomeService;

    @PostMapping
    public ResponseEntity<TransactionIncomeResponse> create(@Valid @RequestBody TransactionIncomeRequestDTO dto) {
        log.debug("creating transaction income");
        System.out.println("create transaction income");
        return ResponseEntity.ok(transactionIncomeService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionIncomeResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(transactionIncomeService.getById(id));
    }


    @GetMapping("/{agentId}/total-transaction-info")
    public ResponseEntity<TotalTransactionDTO> getTotalTransaction(@PathVariable UUID agentId) {
        return ResponseEntity.ok(transactionIncomeService.getTotalByAgentId(agentId));
    }


    @GetMapping("/agent/{agentId}")
    public ResponseEntity<List<TransactionIncomeResponse>> getAllByAgentId(@PathVariable UUID agentId,
                                                                           @RequestParam(required = false) TransactionType type,
                                                                           @RequestParam(required = false) LocalDate fromDate,
                                                                           @RequestParam(required = false) LocalDate toDate,
                                                                           @RequestParam(required = false, defaultValue = "10") int size,
                                                                           @RequestParam(required = false, defaultValue = "0") int page) {

        Pageable pageable = PageRequest.of(page, size);
        System.out.println("ichida");
        return ResponseEntity.ok(transactionIncomeService.getAllByAgentId(agentId, fromDate, toDate, type, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionIncomeResponse> update(@PathVariable UUID id,
                                                            @RequestBody TransactionIncomeRequestDTO dto) {

        return ResponseEntity.ok(transactionIncomeService.update(id, dto));
    }


}