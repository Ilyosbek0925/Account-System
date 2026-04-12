package com.pet.accountsystem.controller;

import com.pet.accountsystem.dto.KpiDto;
import com.pet.accountsystem.dto.request.KpiCreateDto;
import com.pet.accountsystem.dto.response.ApiResponse;
import com.pet.accountsystem.service.KpiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/kpi")
@RequiredArgsConstructor
public class KpiController {

    private final KpiService service;

    @PostMapping
    public ResponseEntity<ApiResponse<KpiDto>> create(@RequestBody KpiCreateDto dto) {
        return ResponseEntity.ok(ApiResponse.<KpiDto>builder()
                .status(201)
                .data(service.create(dto))
                .message("created kpi")
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<KpiDto>>> getAll() {
        return ResponseEntity.ok(ApiResponse.<List<KpiDto>>builder()
                .status(200)
                .data(service.getAll())
                .message("got kpis")
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<KpiDto>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.<KpiDto>builder()
                .status(200)
                .data(service.getById(id))
                .message("got kpi")
                .build());
    }

    @PutMapping
    public ResponseEntity<ApiResponse<KpiDto>> update(@RequestBody KpiDto dto) {
        return ResponseEntity.ok(ApiResponse.<KpiDto>builder()
                .status(200)
                .data(service.update(dto))
                .message("updated kpi")
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.builder()
                .status(203)
                .message("deleted successfully")
                .build());
    }
}