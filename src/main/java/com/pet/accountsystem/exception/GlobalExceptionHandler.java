package com.pet.accountsystem.exception;

import com.pet.accountsystem.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {DataNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiResponse<String>> dataNotFoundExceptionHandler(
            DataNotFoundException e) {

        log.error("Data not found: {}", e.getMessage(), e);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ApiResponse.<String>builder()
                                .status(404)
                                .message("Data not found")
                                .build()
                );
    }


    @ExceptionHandler(value = {UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ApiResponse<String>> forbiddenException(UnauthorizedException e) {

        log.error("Unauthorized access: {}", e.getMessage(), e);

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(
                        ApiResponse.<String>builder()
                                .status(401)
                                .message("User not authorized")
                                .data("User is not authorized or token expired")
                                .build()
                );
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationException(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        log.warn("Validation failed: {}", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.<Map<String, String>>builder()
                .data(errors)
                .message("Validation failed")
                .status(400)
                .build());

    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleGlobalException(Exception ex) {

        log.error("Unexpected server error", ex);

        Map<String, String> response = new HashMap<>();
        response.put("error", "Internal Server Error");
        response.put("message", ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.<Map<String, String>>builder()
                        .status(500)
                        .message("Unexpected error")
                        .data(response)
                        .build());
    }
}
