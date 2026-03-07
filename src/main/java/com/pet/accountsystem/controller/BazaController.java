package com.pet.accountsystem.controller;

import com.pet.accountsystem.dto.request.BazaRequestDTO;
import com.pet.accountsystem.dto.response.ApiResponse;
import com.pet.accountsystem.dto.response.BazaResponseDTO;
import com.pet.accountsystem.service.BazaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/baza")
public class BazaController {

    private final BazaService bazaService;

    @PostMapping
    public ResponseEntity<BazaResponseDTO> create(@RequestBody BazaRequestDTO dto) {
        return ResponseEntity.ok(bazaService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BazaResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(bazaService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<BazaResponseDTO>> getAll() {
        return ResponseEntity.ok(bazaService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BazaResponseDTO> update(@PathVariable UUID id, @RequestBody BazaRequestDTO dto) {
        return ResponseEntity.ok(bazaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable UUID id) {
        bazaService.deleteById(id);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("baza deleted successfully")
                        .status(203)
                        .build()
        );
    }
}