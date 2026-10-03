package com.app.ecom.productservice.kafka.event;

public record DecreaseStockEvent(
        Long productId,
        Integer quantity
) {
}
