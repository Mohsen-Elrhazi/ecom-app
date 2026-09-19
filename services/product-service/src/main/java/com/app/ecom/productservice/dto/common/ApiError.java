package com.app.ecom.productservice.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@AllArgsConstructor
@Data
@Builder
public class ApiError {
    private boolean success;
    private String message;
    private int status;
    private Map<String, String> errors;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
}
