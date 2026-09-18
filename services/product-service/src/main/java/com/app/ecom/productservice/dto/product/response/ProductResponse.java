package com.app.ecom.productservice.dto.product.response;

import com.app.ecom.productservice.dto.category.response.CategoryResponse;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        String description,
        BigDecimal availableQuantity,
        Integer price,
        CategoryResponse category
) {
}
