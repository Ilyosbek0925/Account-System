package com.pet.accountsystem.controller;

import com.pet.accountsystem.dto.request.TransactionIncomeRequestDTO;
import com.pet.accountsystem.dto.response.TransactionIncomeResponseDTO;
import com.pet.accountsystem.dto.response.ApiResponse;
import com.pet.accountsystem.service.TransactionIncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/transaction-income")
public class TransactionIncomeController {

    private final TransactionIncomeService transactionIncomeService;

    @PostMapping
    public ResponseEntity<TransactionIncomeResponseDTO> create(@RequestBody TransactionIncomeRequestDTO dto) {
        return ResponseEntity.ok(transactionIncomeService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionIncomeResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(transactionIncomeService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<TransactionIncomeResponseDTO>> getAll() {
        return ResponseEntity.ok(transactionIncomeService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionIncomeResponseDTO> update(@PathVariable UUID id,
                                                               @RequestBody TransactionIncomeRequestDTO dto) {
        return ResponseEntity.ok(transactionIncomeService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable UUID id) {
        transactionIncomeService.deleteById(id);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("transaction income deleted successfully")
                        .status(203)
                        .build()
        );
    }
}