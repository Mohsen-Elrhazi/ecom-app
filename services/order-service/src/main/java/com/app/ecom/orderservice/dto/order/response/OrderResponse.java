package com.app.ecom.orderservice.dto.order.response;

import com.app.ecom.orderservice.dto.orderItem.response.OrderItemResponse;
import com.app.ecom.orderservice.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        Long id,
        String customerId,
        String reference,
        BigDecimal totalAmount,
        OrderStatus status,
        List<OrderItemResponse> items,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
