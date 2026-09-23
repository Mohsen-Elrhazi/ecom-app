package com.app.ecom.orderservice.dto.product.response;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        String description,
        Integer availableQuantity,
        BigDecimal price
) {
}
