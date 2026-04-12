package com.pet.accountsystem.controller;

import com.pet.accountsystem.dto.response.ApiResponse;
import com.pet.accountsystem.dto.response.ClientBalanceResponse;
import com.pet.accountsystem.service.impl.ClientBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/client-balance")
@RequiredArgsConstructor
public class ClientBalanceController {

    private final ClientBalanceService service;

    @GetMapping("/{clientId}")
    public ResponseEntity<ApiResponse<ClientBalanceResponse>> getById(@PathVariable UUID clientId) {
        return ResponseEntity.ok(ApiResponse.<ClientBalanceResponse>builder()
                .data(service.getById(clientId))
                .status(200)
                .message("got client balance")
                .build());


    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ClientBalanceResponse>>> getAll() {

        List<ClientBalanceResponse> all = service.getAll();
        return ResponseEntity.ok(ApiResponse.<List<ClientBalanceResponse>>builder()
                .data(all)
                .message("getting client balances")
                .status(200)
                .build());
    }

}