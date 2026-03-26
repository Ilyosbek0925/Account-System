package com.pet.accountsystem.controller;

import com.pet.accountsystem.dto.request.BazaRequestDTO;
import com.pet.accountsystem.dto.response.ApiResponse;
import com.pet.accountsystem.dto.response.BazaResponseDTO;
import com.pet.accountsystem.service.BazaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/base")
public class BaseController {

    private final BazaService bazaService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<BazaResponseDTO>> create(@Valid @RequestBody BazaRequestDTO dto) {

        BazaResponseDTO response = bazaService.create(dto);

        return ResponseEntity.ok(
                ApiResponse.<BazaResponseDTO>builder()
                        .message("Base created successfully")
                        .data(response)
                        .status(201)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BazaResponseDTO>> getById(@PathVariable UUID id) {

        BazaResponseDTO response = bazaService.getById(id);

        return ResponseEntity.ok(
                ApiResponse.<BazaResponseDTO>builder()
                        .message("Base fetched successfully")
                        .data(response)
                        .status(200)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<BazaResponseDTO>>> getAll() {

        List<BazaResponseDTO> list = bazaService.getAll();

        return ResponseEntity.ok(
                ApiResponse.<List<BazaResponseDTO>>builder()
                        .message("Bases fetched successfully")
                        .data(list)
                        .status(200)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BazaResponseDTO>> update(
            @PathVariable UUID id,
            @RequestBody BazaRequestDTO dto) {

        BazaResponseDTO updated = bazaService.update(id, dto);

        return ResponseEntity.ok(
                ApiResponse.<BazaResponseDTO>builder()
                        .message("Base updated successfully")
                        .data(updated)
                        .status(200)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {

        bazaService.deleteById(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .message("Base deleted successfully")
                        .status(204)
                        .build()
        );
    }
}