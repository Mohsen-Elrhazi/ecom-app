package com.app.ecom.productservice.dto.product.request;

import java.math.BigDecimal;

public record UpdateProductRequest(
        String name,
        String description,
        BigDecimal price,
        Integer availableQuantity,
        Long categoryId
) {
}
