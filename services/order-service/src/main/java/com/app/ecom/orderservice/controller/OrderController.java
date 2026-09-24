package com.app.ecom.orderservice.controller;

import com.app.ecom.orderservice.dto.common.ApiResponse;
import com.app.ecom.orderservice.dto.order.request.CreateOrderRequest;
import com.app.ecom.orderservice.dto.order.response.OrderResponse;

import com.app.ecom.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

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

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getAll(){
        List<OrderResponse> orders = orderService.getAll();

        ApiResponse<List<OrderResponse>> response = ApiResponse.<List<OrderResponse>>builder()
                .success(true)
                .message("orders retrieved successfully")
                .data(orders)
                .build();

        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
