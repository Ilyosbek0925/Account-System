package com.pet.accountsystem.controller;

import com.pet.accountsystem.dto.request.AgentBalanceRequestDTO;
import com.pet.accountsystem.dto.response.AgentBalanceResponseDTO;
import com.pet.accountsystem.dto.response.ApiResponse;
import com.pet.accountsystem.dto.response.TransactionTypeSummaryResponse;
import com.pet.accountsystem.projection.TransactionTypeSummary;
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
    public ResponseEntity<ApiResponse<AgentBalanceResponseDTO>> create(@RequestBody AgentBalanceRequestDTO dto) {

        AgentBalanceResponseDTO response = agentBalanceService.create(dto);

        return ResponseEntity.ok(
                ApiResponse.<AgentBalanceResponseDTO>builder()
                        .message("Agent balance created successfully")
                        .data(response)
                        .status(201)
                        .build()
        );
    }

    @GetMapping("/{agentId}")
    public ResponseEntity<ApiResponse<AgentBalanceResponseDTO>> getById(@PathVariable UUID agentId) {
AgentBalanceResponseDTO agentBalanceResponse= agentBalanceService.getAgentBalanceByAgentId(agentId);


        return ResponseEntity.ok(
                ApiResponse.<AgentBalanceResponseDTO>builder()
                        .message("Agent balance fetched successfully")
                        .data(agentBalanceResponse)
                        .status(200)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AgentBalanceResponseDTO>>> getAll() {

        List<AgentBalanceResponseDTO> response = agentBalanceService.getAll();

        return ResponseEntity.ok(
                ApiResponse.<List<AgentBalanceResponseDTO>>builder()
                        .message("Agent balances fetched successfully")
                        .data(response)
                        .status(200)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AgentBalanceResponseDTO>> update(
            @PathVariable UUID id,
            @RequestBody AgentBalanceRequestDTO dto) {

        AgentBalanceResponseDTO response = agentBalanceService.update(id, dto);

        return ResponseEntity.ok(
                ApiResponse.<AgentBalanceResponseDTO>builder()
                        .message("Agent balance updated successfully")
                        .data(response)
                        .status(200)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {

        agentBalanceService.deleteById(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .message("Agent balance deleted successfully")
                        .status(204)
                        .build()
        );
    }
}