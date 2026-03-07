package com.pet.accountsystem.controller;

import com.pet.accountsystem.dto.request.AdminBalanceRequestDTO;
import com.pet.accountsystem.dto.response.AdminBalanceResponseDTO;
import com.pet.accountsystem.dto.response.ApiResponse;
import com.pet.accountsystem.service.AdminBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin-balance")
public class AdminBalanceController {

    private final AdminBalanceService adminBalanceService;

    @PostMapping
    public ResponseEntity<AdminBalanceResponseDTO> create(@RequestBody AdminBalanceRequestDTO dto) {
        return ResponseEntity.ok(adminBalanceService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminBalanceResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(adminBalanceService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<AdminBalanceResponseDTO>> getAll() {
        return ResponseEntity.ok(adminBalanceService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminBalanceResponseDTO> update(@PathVariable UUID id,
                                                          @RequestBody AdminBalanceRequestDTO dto) {
        return ResponseEntity.ok(adminBalanceService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable UUID id) {
        adminBalanceService.deleteById(id);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("admin balance deleted successfully")
                        .status(203)
                        .build()
        );
    }
}