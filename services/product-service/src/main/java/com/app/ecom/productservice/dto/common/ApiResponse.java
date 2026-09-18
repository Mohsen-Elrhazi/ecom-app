package com.app.ecom.productservice.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@Builder
public class ApiResponse <T>{
    private boolean success;
    private String message;
    private  T data;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
}
