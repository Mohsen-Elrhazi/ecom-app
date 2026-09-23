package com.app.ecom.orderservice.service;

import com.app.ecom.orderservice.dto.order.request.CreateOrderRequest;
import com.app.ecom.orderservice.dto.order.response.OrderResponse;

public interface OrderService {
    OrderResponse create(CreateOrderRequest request);
}
