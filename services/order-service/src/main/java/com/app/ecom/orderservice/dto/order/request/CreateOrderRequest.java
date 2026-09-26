package com.app.ecom.orderservice.dto.order.request;

import com.app.ecom.orderservice.dto.orderItem.request.OrderItemRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateOrderRequest(

        @NotEmpty(message = "Order must contain at least one item")
        List<OrderItemRequest> items
) {
}
