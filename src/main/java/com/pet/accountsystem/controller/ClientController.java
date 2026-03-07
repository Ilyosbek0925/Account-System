package com.pet.accountsystem.controller;

import com.pet.accountsystem.dto.request.ClientRequestDTO;
import com.pet.accountsystem.dto.response.ApiResponse;
import com.pet.accountsystem.dto.response.ClientResponseDTO;
import com.pet.accountsystem.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientResponseDTO> createClient(@RequestBody ClientRequestDTO requestDTO) {
        ClientResponseDTO clientResponseDTO = clientService.createClient(requestDTO);
        return ResponseEntity.ok(clientResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> getById(@PathVariable UUID id) {
        ClientResponseDTO dto = clientService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> getAll() {
        List<ClientResponseDTO> list = clientService.getAll();
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> updateClient(@PathVariable UUID id,
                                                          @RequestBody ClientRequestDTO requestDTO) {
        ClientResponseDTO updatedClient = clientService.update(id, requestDTO);
        return ResponseEntity.ok(updatedClient);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteClient(@PathVariable UUID id) {
        clientService.deleteById(id);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("client deleted successfully")
                        .status(203)
                        .build()
        );
    }
}