package com.app.ecom.orderservice.service.impl;

import com.app.ecom.orderservice.client.ProductServiceClient;
import com.app.ecom.orderservice.dto.order.request.CreateOrderRequest;
import com.app.ecom.orderservice.dto.order.response.OrderResponse;
import com.app.ecom.orderservice.dto.orderItem.request.OrderItemRequest;
import com.app.ecom.orderservice.dto.product.request.DecreaseStockRequest;
import com.app.ecom.orderservice.dto.product.response.ProductResponse;
import com.app.ecom.orderservice.entity.Order;
import com.app.ecom.orderservice.entity.OrderItem;
import com.app.ecom.orderservice.enums.OrderStatus;
import com.app.ecom.orderservice.mapper.OrderMapper;
import com.app.ecom.orderservice.repository.OrderRepository;
import com.app.ecom.orderservice.service.OrderService;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductIntegrationServiceImpl productIntegrationService;

    @Override
    public OrderResponse create(CreateOrderRequest request) {
        Order order = new Order();
        order.setCustomerId(request.customerId());
        order.setReference("ORD-"+ UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        order.setStatus(OrderStatus.PENDING);
        BigDecimal totalAmount = BigDecimal.ZERO;

       for( OrderItemRequest item: request.items()){
           ProductResponse product = productIntegrationService.getProduct(item.productId()) ;

           productIntegrationService.decreaseStock(product.id(),new DecreaseStockRequest(item.quantity()));

           BigDecimal unitPrice = product.price();
           BigDecimal subTotal = unitPrice.multiply(BigDecimal.valueOf(item.quantity()));

           OrderItem orderItem = new OrderItem();

           orderItem.setOrder(order);
           orderItem.setProductId(item.productId());
           orderItem.setQuantity(item.quantity());
           orderItem.setUnitPrice(unitPrice);
           orderItem.setSubTotal(subTotal);

           order.getItems().add(orderItem);

           totalAmount = totalAmount.add(subTotal);
       }

       order.setTotalAmount(totalAmount);

       Order savedOrder = orderRepository.save(order);

       return orderMapper.toResponse(savedOrder);
    }
}
