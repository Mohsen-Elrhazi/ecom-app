package com.app.ecom.orderservice.service.impl;

import com.app.ecom.orderservice.client.ProductServiceClient;
import com.app.ecom.orderservice.dto.order.request.CreateOrderRequest;
import com.app.ecom.orderservice.dto.order.response.OrderResponse;
import com.app.ecom.orderservice.dto.orderItem.request.OrderItemRequest;
import com.app.ecom.orderservice.dto.product.response.ProductResponse;
import com.app.ecom.orderservice.entity.Order;
import com.app.ecom.orderservice.entity.OrderItem;
import com.app.ecom.orderservice.enums.OrderStatus;
import com.app.ecom.orderservice.exception.InsufficientStockException;
import com.app.ecom.orderservice.exception.ResourceNotFoundException;
import com.app.ecom.orderservice.mapper.OrderMapper;
import com.app.ecom.orderservice.repository.OrderRepository;
import com.app.ecom.orderservice.service.OrderService;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductServiceClient productServiceClient;


    private ProductResponse getProduct(Long productId){
        try {
            return productServiceClient.getProductById(productId).getData();
        }catch (FeignException.NotFound ex){
            throw new ResourceNotFoundException("product not found with this id: " + productId);
        }
    }

    @Override
    public OrderResponse create(CreateOrderRequest request) {
        Order order = new Order();
        order.setCustomerId(request.customerId());
        order.setReference("REF1234");
        order.setStatus(OrderStatus.PENDING);
        BigDecimal totalAmount = BigDecimal.ZERO;

       for( OrderItemRequest item: request.items()){
           ProductResponse product = getProduct(item.productId()) ;

           if(item.quantity() > product.availableQuantity()){
               throw new InsufficientStockException("stock insufficient for product with id: " + item.productId());
           }

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
