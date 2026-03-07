package com.pet.accountsystem.controller;

import com.pet.accountsystem.dto.request.AgentBalanceRequestDTO;
import com.pet.accountsystem.dto.response.AgentBalanceResponseDTO;
import com.pet.accountsystem.dto.response.ApiResponse;
import com.pet.accountsystem.service.AgentBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/agent-balance")
public class AgentBalanceController {

    private final AgentBalanceService agentBalanceService;

    @PostMapping
    public ResponseEntity<AgentBalanceResponseDTO> create(@RequestBody AgentBalanceRequestDTO dto) {
        return ResponseEntity.ok(agentBalanceService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgentBalanceResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(agentBalanceService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<AgentBalanceResponseDTO>> getAll() {
        return ResponseEntity.ok(agentBalanceService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgentBalanceResponseDTO> update(@PathVariable UUID id,
                                                          @RequestBody AgentBalanceRequestDTO dto) {
        return ResponseEntity.ok(agentBalanceService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable UUID id) {
        agentBalanceService.deleteById(id);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("agent balance deleted successfully")
                        .status(203)
                        .build()
        );
    }
}