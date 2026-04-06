package com.pet.accountsystem.controller;

import com.pet.accountsystem.dto.request.ClientRequestDTO;
import com.pet.accountsystem.dto.response.ApiResponse;
import com.pet.accountsystem.dto.response.ClientResponseDTO;
import com.pet.accountsystem.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService clientService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<ClientResponseDTO>> createClient(@RequestBody ClientRequestDTO requestDTO) {

        ClientResponseDTO response = clientService.createClient(requestDTO);

        return ResponseEntity.ok(
                ApiResponse.<ClientResponseDTO>builder()
                        .message("Client created successfully")
                        .data(response)
                        .status(201)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ClientResponseDTO>> getById(@PathVariable UUID id) {

        ClientResponseDTO response = clientService.getById(id);

        return ResponseEntity.ok(
                ApiResponse.<ClientResponseDTO>builder()
                        .message("Client fetched successfully")
                        .data(response)
                        .status(200)
                        .build()
        );
    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<ClientResponseDTO>>> getAll() {

        List<ClientResponseDTO> list = clientService.getAll();

        return ResponseEntity.ok(
                ApiResponse.<List<ClientResponseDTO>>builder()
                        .message("Clients fetched successfully")
                        .data(list)
                        .status(200)
                        .build()
        );
    }

    @GetMapping("base/{baseId}")
    public ResponseEntity<ApiResponse<List<ClientResponseDTO>>> getByBaseId(@PathVariable UUID baseId,
                                                                             @RequestParam(required = false, defaultValue = "10") int size,
                                                                             @RequestParam(required = false, defaultValue = "0") int page) {


        Pageable pageable = PageRequest.of(page, size);
        List<ClientResponseDTO> list = clientService.getByAgent(baseId,pageable);

        return ResponseEntity.ok(
                ApiResponse.<List<ClientResponseDTO>>builder()
                        .message("Clients fetched successfully")
                        .data(list)
                        .status(200)
                        .build()
        );
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ClientResponseDTO>> updateClient(
            @PathVariable UUID id,
            @RequestBody ClientRequestDTO requestDTO) {

        ClientResponseDTO updated = clientService.update(id, requestDTO);

        return ResponseEntity.ok(
                ApiResponse.<ClientResponseDTO>builder()
                        .message("Client updated successfully")
                        .data(updated)
                        .status(200)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteClient(@PathVariable UUID id) {

        clientService.deleteById(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .message("Client deleted successfully")
                        .status(204)
                        .build()
        );
    }
}