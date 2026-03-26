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
    public ResponseEntity<ApiResponse<TransactionInkassaResponseDTO>> create(
            @RequestBody TransactionInkassaRequestDTO dto) {

        TransactionInkassaResponseDTO response = transactionInkassaService.create(dto);

        return ResponseEntity.status(201).body(
                ApiResponse.<TransactionInkassaResponseDTO>builder()
                        .message("Transaction inkassa created successfully")
                        .data(response)
                        .status(201)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TransactionInkassaResponseDTO>> getById(@PathVariable UUID id) {

        TransactionInkassaResponseDTO response = transactionInkassaService.getById(id);

        return ResponseEntity.ok(
                ApiResponse.<TransactionInkassaResponseDTO>builder()
                        .message("Transaction inkassa fetched successfully")
                        .data(response)
                        .status(200)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TransactionInkassaResponseDTO>>> getAll() {

        List<TransactionInkassaResponseDTO> response = transactionInkassaService.getAll();

        return ResponseEntity.ok(
                ApiResponse.<List<TransactionInkassaResponseDTO>>builder()
                        .message("Transaction inkassas fetched successfully")
                        .data(response)
                        .status(200)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TransactionInkassaResponseDTO>> update(
            @PathVariable UUID id,
            @RequestBody TransactionInkassaRequestDTO dto) {

        TransactionInkassaResponseDTO response = transactionInkassaService.update(id, dto);

        return ResponseEntity.ok(
                ApiResponse.<TransactionInkassaResponseDTO>builder()
                        .message("Transaction inkassa updated successfully")
                        .data(response)
                        .status(200)
                        .build()
        );
    }
}