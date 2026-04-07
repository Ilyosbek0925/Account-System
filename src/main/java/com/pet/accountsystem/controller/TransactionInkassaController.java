package com.pet.accountsystem.controller;

import com.pet.accountsystem.dto.request.TransactionInkassaRequestDTO;
import com.pet.accountsystem.dto.response.TransactionInkassaResponseDTO;
import com.pet.accountsystem.dto.response.ApiResponse;
import com.pet.accountsystem.service.TransactionInkassaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/transaction-inkassa")
public class TransactionInkassaController {

    private final TransactionInkassaService transactionInkassaService;

    @PostMapping()
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


    @GetMapping("admin/{adminId}")
    public ResponseEntity<ApiResponse<List<TransactionInkassaResponseDTO>>> getTransactionsByAdmin(@PathVariable UUID adminId,
                                                                                                   @RequestParam(required = false, defaultValue = "10") int size,
                                                                                                   @RequestParam(required = false, defaultValue = "0") int page,
                                                                                                   @RequestParam(required = false) LocalDate fromDate,
                                                                                                   @RequestParam(required = false) LocalDate toDate) {

        Pageable pageRequest = PageRequest.of(page, size);
        List<TransactionInkassaResponseDTO> list = transactionInkassaService.getTransactionInkasssaByAdminId(adminId, fromDate, toDate, pageRequest);
        return ResponseEntity.ok(ApiResponse.<List<TransactionInkassaResponseDTO>>builder()
                .status(200)
                .message("fetching transaction inkassa successfully")
                .data(list)
                .build());
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