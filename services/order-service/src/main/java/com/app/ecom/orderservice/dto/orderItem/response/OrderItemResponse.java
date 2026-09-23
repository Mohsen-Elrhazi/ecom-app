package com.app.ecom.orderservice.dto.orderItem.response;

import java.math.BigDecimal;

public record OrderItemResponse(
        Long id,
        Long productId,
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal subTotal
) {
}
