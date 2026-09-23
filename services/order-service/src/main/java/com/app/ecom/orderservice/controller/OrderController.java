package com.app.ecom.orderservice.controller;

import com.app.ecom.orderservice.client.ProductServiceClient;
import com.app.ecom.orderservice.dto.common.ApiResponse;
import com.app.ecom.orderservice.dto.order.request.CreateOrderRequest;
import com.app.ecom.orderservice.dto.order.response.OrderResponse;
import com.app.ecom.orderservice.dto.product.response.ProductResponse;
import com.app.ecom.orderservice.exception.ResourceNotFoundException;
import com.app.ecom.orderservice.service.OrderService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public String test(){
        return "Hello from order-service test";
    }

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> testProduct(@RequestBody CreateOrderRequest request) {
        OrderResponse order = orderService.create(request);

        ApiResponse<OrderResponse> response = ApiResponse.<OrderResponse>builder()
                .success(true)
                .message("order created succeffuly")
                .data(order)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
