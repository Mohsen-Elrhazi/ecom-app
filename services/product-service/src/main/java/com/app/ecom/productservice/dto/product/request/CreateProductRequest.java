package com.app.ecom.productservice.dto.product.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateProductRequest(

        @NotBlank(message = "Name is required")
        String name,

        String description,

        @NotNull(message = "Available quantity is required")
        @Min(value = 0, message = "Available quantity must be greater than or equal to 0")
        Integer availableQuantity,

        @NotNull(message = "Price is required")
        BigDecimal price,

        Long categoryId
) {
}
