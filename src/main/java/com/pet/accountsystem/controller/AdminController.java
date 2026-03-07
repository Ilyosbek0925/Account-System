package com.pet.accountsystem.controller;

import com.pet.accountsystem.dto.request.AdminRequestDTO;
import com.pet.accountsystem.dto.response.AdminResponseDTO;
import com.pet.accountsystem.dto.response.ApiResponse;
import com.pet.accountsystem.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    @PostMapping
    public ResponseEntity<AdminResponseDTO> create(@RequestBody AdminRequestDTO dto) {
        return ResponseEntity.ok(adminService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(adminService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<AdminResponseDTO>> getAll() {
        return ResponseEntity.ok(adminService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminResponseDTO> update(@PathVariable UUID id,
                                                   @RequestBody AdminRequestDTO dto) {
        return ResponseEntity.ok(adminService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable UUID id) {
        adminService.deleteById(id);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("admin deleted successfully")
                        .status(203)
                        .build()
        );
    }
}