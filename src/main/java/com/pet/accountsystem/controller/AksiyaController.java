package com.pet.accountsystem.controller;

import com.pet.accountsystem.dto.AksiyaDto;
import com.pet.accountsystem.dto.request.AksiyaCreateDto;
import com.pet.accountsystem.dto.response.ApiResponse;
import com.pet.accountsystem.service.AksiyaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/aksiya")
@RequiredArgsConstructor
public class AksiyaController {

    private final AksiyaService aksiyaService;

    @PostMapping
    public ResponseEntity<ApiResponse<AksiyaDto>> create(@RequestBody AksiyaCreateDto dto) {
        AksiyaDto aksiyaDto = aksiyaService.create(dto);
        return ResponseEntity.ok(ApiResponse.<AksiyaDto>builder()
                .status(201)
                .data(aksiyaDto)
                .message("created aksiya")
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AksiyaDto>>> getAll() {
        List<AksiyaDto> all = aksiyaService.getAll();
        return ResponseEntity.ok(ApiResponse.<List<AksiyaDto>>builder()
                .status(200)
                .data(all)
                .message("got aksiya")
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AksiyaDto>> getById(@PathVariable UUID id) {
        AksiyaDto byId = aksiyaService.getById(id);
        return ResponseEntity.ok(ApiResponse.<AksiyaDto>builder()
                .status(200)
                .data(byId)
                .message("got aksiya")
                .build());
    }

    @PutMapping
    public ResponseEntity<ApiResponse<AksiyaDto>> update(@RequestBody AksiyaDto dto) {
        AksiyaDto update = aksiyaService.update(dto);
        return ResponseEntity.ok(ApiResponse.<AksiyaDto>builder()
                .status(200)
                .data(update)
                .message("update aksiya")
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable UUID id) {
        aksiyaService.delete(id);
        return ResponseEntity.ok(ApiResponse.builder()
                .message("deleted successfully")
                .status(203)
                .build());
    }
}
