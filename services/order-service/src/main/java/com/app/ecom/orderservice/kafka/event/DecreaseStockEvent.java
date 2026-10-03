package com.app.ecom.orderservice.kafka.event;

public record DecreaseStockEvent(
        Long productId,
        Integer quantity
) {
}
