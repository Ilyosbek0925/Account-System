package com.pet.accountsystem.controller;

import com.pet.accountsystem.dto.request.AgentRequestDTO;
import com.pet.accountsystem.dto.response.AgentResponseDTO;
import com.pet.accountsystem.dto.response.ApiResponse;
import com.pet.accountsystem.service.AgentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    private  AgentService agentService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<AgentResponseDTO> create(@Valid @RequestBody AgentRequestDTO requestDTO) {
        log.warn("create agent \n\n");
        AgentResponseDTO responseDTO = agentService.create(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }




    @GetMapping("/{id}")
    public ResponseEntity<AgentResponseDTO> getById(@PathVariable UUID id) {
        AgentResponseDTO responseDTO = agentService.getById(id);
        return ResponseEntity.ok(responseDTO);
    }




    @GetMapping("/{id}/profile")
    public ResponseEntity<AgentResponseDTO> getAgentProfile(@PathVariable UUID id) {
        return null;
    }


    @GetMapping("/{id}/recent-activity")
    public ResponseEntity<?> getAgentRecentActivity(@PathVariable UUID id) {
    return null;
    }



    @GetMapping
    public ResponseEntity<List<AgentResponseDTO>> getAll() {
        List<AgentResponseDTO> list = agentService.getAll();
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgentResponseDTO> update(@PathVariable UUID id,
                                                   @RequestBody AgentRequestDTO requestDTO) {
        AgentResponseDTO updated = agentService.update(id, requestDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable UUID id) {
        agentService.deleteById(id);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("agent deleted successfully")
                        .status(203)
                        .build()
        );
    }
}