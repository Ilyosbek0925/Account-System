package com.pet.accountsystem.controller;

import com.pet.accountsystem.dto.AgentSalaryDto;
import com.pet.accountsystem.dto.request.AgentSalaryCreateDto;
import com.pet.accountsystem.dto.response.ApiResponse;
import com.pet.accountsystem.service.AgentSalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/agent-salary")
@RequiredArgsConstructor
public class AgentSalaryController {

    private final AgentSalaryService service;

    public ResponseEntity<ApiResponse<AgentSalaryDto>> create(@RequestBody AgentSalaryCreateDto dto) {
        return ResponseEntity.ok(ApiResponse.<AgentSalaryDto>builder()
                .status(201)
                .data(service.create(dto))
                .message("created agent salary")
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AgentSalaryDto>>> getAll() {
        return ResponseEntity.ok(ApiResponse.<List<AgentSalaryDto>>builder()
                .status(200)
                .data(service.getAll())
                .message("got agent salaries")
                .build());
    }

    @GetMapping("/{agentId}")
    public ResponseEntity<ApiResponse<AgentSalaryDto>> getById(@PathVariable UUID agentId) {
        return ResponseEntity.ok(ApiResponse.<AgentSalaryDto>builder()
                .status(200)
                .data(service.getById(agentId))
                .message("got agent salary")
                .build());
    }

    public ResponseEntity<ApiResponse<AgentSalaryDto>> update(@RequestBody AgentSalaryDto dto) {
        return ResponseEntity.ok(ApiResponse.<AgentSalaryDto>builder()
                .status(200)
                .data(service.update(dto))
                .message("updated agent salary")
                .build());
    }

    public ResponseEntity<ApiResponse<?>> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.builder()
                .status(203)
                .message("deleted successfully")
                .build());
    }
}