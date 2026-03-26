package com.pet.accountsystem.controller;

import com.pet.accountsystem.dto.request.AgentRequestDTO;
import com.pet.accountsystem.dto.response.AgentResponseDTO;
import com.pet.accountsystem.dto.response.ApiResponse;
import com.pet.accountsystem.service.AgentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/agent")
public class AgentController {

    private final AgentService agentService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<AgentResponseDTO>> create(@Valid @RequestBody AgentRequestDTO requestDTO) {

        log.warn("create agent");

        AgentResponseDTO response = agentService.create(requestDTO);

        return ResponseEntity.ok(
                ApiResponse.<AgentResponseDTO>builder()
                        .message("Agent created successfully")
                        .data(response)
                        .status(201)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AgentResponseDTO>> getById(@PathVariable UUID id) {

        AgentResponseDTO response = agentService.getById(id);

        return ResponseEntity.ok(
                ApiResponse.<AgentResponseDTO>builder()
                        .message("Agent fetched successfully")
                        .data(response)
                        .status(200)
                        .build()
        );
    }

    @GetMapping("/{id}/profile")
    public ResponseEntity<ApiResponse<AgentResponseDTO>> getAgentProfile(@PathVariable UUID id) {

        AgentResponseDTO response = agentService.getById(id);

        return ResponseEntity.ok(
                ApiResponse.<AgentResponseDTO>builder()
                        .message("Agent profile fetched successfully")
                        .data(response)
                        .status(200)
                        .build()
        );
    }

    @GetMapping("/{id}/recent-activity")
    public ResponseEntity<ApiResponse<Object>> getAgentRecentActivity(@PathVariable UUID id) {

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("Agent recent activity fetched successfully")
                        .data(null)
                        .status(200)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AgentResponseDTO>>> getAll() {

        List<AgentResponseDTO> list = agentService.getAll();

        return ResponseEntity.ok(
                ApiResponse.<List<AgentResponseDTO>>builder()
                        .message("Agents fetched successfully")
                        .data(list)
                        .status(200)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AgentResponseDTO>> update(
            @PathVariable UUID id,
            @RequestBody AgentRequestDTO requestDTO) {

        AgentResponseDTO updated = agentService.update(id, requestDTO);

        return ResponseEntity.ok(
                ApiResponse.<AgentResponseDTO>builder()
                        .message("Agent updated successfully")
                        .data(updated)
                        .status(200)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {

        agentService.deleteById(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .message("Agent deleted successfully")
                        .status(204)
                        .build()
        );
    }
}