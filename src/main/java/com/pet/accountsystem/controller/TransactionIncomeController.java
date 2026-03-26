package com.pet.accountsystem.controller;

import com.pet.accountsystem.dto.TotalTransactionDTO;
import com.pet.accountsystem.dto.request.TransactionIncomeRequestDTO;
import com.pet.accountsystem.dto.response.ApiResponse;
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
    public ResponseEntity<ApiResponse<TransactionIncomeResponse>> create(
            @Valid @RequestBody TransactionIncomeRequestDTO dto
    ) {
        log.debug("Creating transaction income");

        TransactionIncomeResponse response = transactionIncomeService.create(dto);

        return ResponseEntity.ok(
                ApiResponse.<TransactionIncomeResponse>builder()
                        .message("Transaction income created successfully")
                        .data(response)
                        .status(201)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TransactionIncomeResponse>> getById(@PathVariable UUID id) {

        TransactionIncomeResponse response = transactionIncomeService.getById(id);

        return ResponseEntity.ok(
                ApiResponse.<TransactionIncomeResponse>builder()
                        .message("Transaction income fetched successfully")
                        .data(response)
                        .status(200)
                        .build()
        );
    }

    @GetMapping("/{agentId}/total-transaction-info")
    public ResponseEntity<ApiResponse<TotalTransactionDTO>> getTotalTransaction(@PathVariable UUID agentId) {

        TotalTransactionDTO response = transactionIncomeService.getTotalByAgentId(agentId);

        return ResponseEntity.ok(
                ApiResponse.<TotalTransactionDTO>builder()
                        .message("Total transaction info fetched successfully")
                        .data(response)
                        .status(200)
                        .build()
        );
    }

    @GetMapping("/agent/{agentId}")
    public ResponseEntity<ApiResponse<List<TransactionIncomeResponse>>> getAllByAgentId(
            @PathVariable UUID agentId,
            @RequestParam(required = false) TransactionType type,
            @RequestParam(required = false) LocalDate fromDate,
            @RequestParam(required = false) LocalDate toDate,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "0") int page
    ) {
        Pageable pageable = PageRequest.of(page, size);

        List<TransactionIncomeResponse> response =
                transactionIncomeService.getAllByAgentId(agentId, fromDate, toDate, type, pageable);

        return ResponseEntity.ok(
                ApiResponse.<List<TransactionIncomeResponse>>builder()
                        .message("Transaction incomes fetched successfully")
                        .data(response)
                        .status(200)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TransactionIncomeResponse>> update(
            @PathVariable UUID id,
            @RequestBody TransactionIncomeRequestDTO dto
    ) {
        TransactionIncomeResponse response = transactionIncomeService.update(id, dto);

        return ResponseEntity.ok(
                ApiResponse.<TransactionIncomeResponse>builder()
                        .message("Transaction income updated successfully")
                        .data(response)
                        .status(200)
                        .build()
        );
    }
}