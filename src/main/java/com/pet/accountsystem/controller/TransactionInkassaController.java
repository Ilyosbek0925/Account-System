package com.pet.accountsystem.controller;

import com.pet.accountsystem.dto.request.TransactionInkassaRequestDTO;
import com.pet.accountsystem.dto.response.TransactionInkassaResponseDTO;
import com.pet.accountsystem.dto.response.ApiResponse;
import com.pet.accountsystem.service.TransactionInkassaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/transaction-inkassa")
public class TransactionInkassaController {

    private final TransactionInkassaService transactionInkassaService;

    @PostMapping
    public ResponseEntity<TransactionInkassaResponseDTO> create(@RequestBody TransactionInkassaRequestDTO dto) {
        return ResponseEntity.ok(transactionInkassaService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionInkassaResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(transactionInkassaService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<TransactionInkassaResponseDTO>> getAll() {
        return ResponseEntity.ok(transactionInkassaService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionInkassaResponseDTO> update(@PathVariable UUID id,
                                                                @RequestBody TransactionInkassaRequestDTO dto) {
        return ResponseEntity.ok(transactionInkassaService.update(id, dto));
    }


}