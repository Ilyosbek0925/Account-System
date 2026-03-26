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
    public ResponseEntity<ApiResponse<AdminResponseDTO>> create(@RequestBody AdminRequestDTO dto) {

        AdminResponseDTO response = adminService.create(dto);

        return ResponseEntity.ok(
                ApiResponse.<AdminResponseDTO>builder()
                        .message("Admin created successfully")
                        .data(response)
                        .status(201)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AdminResponseDTO>> getById(@PathVariable UUID id) {

        AdminResponseDTO response = adminService.getById(id);

        return ResponseEntity.ok(
                ApiResponse.<AdminResponseDTO>builder()
                        .message("Admin fetched successfully")
                        .data(response)
                        .status(200)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AdminResponseDTO>>> getAll() {

        List<AdminResponseDTO> response = adminService.getAll();

        return ResponseEntity.ok(
                ApiResponse.<List<AdminResponseDTO>>builder()
                        .message("Admins fetched successfully")
                        .data(response)
                        .status(200)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AdminResponseDTO>> update(
            @PathVariable UUID id,
            @RequestBody AdminRequestDTO dto) {

        AdminResponseDTO response = adminService.update(id, dto);

        return ResponseEntity.ok(
                ApiResponse.<AdminResponseDTO>builder()
                        .message("Admin updated successfully")
                        .data(response)
                        .status(200)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {

        adminService.deleteById(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .message("Admin deleted successfully")
                        .status(204)
                        .build()
        );
    }
}