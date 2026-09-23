package com.app.ecom.orderservice.mapper;

import com.app.ecom.orderservice.dto.order.request.CreateOrderRequest;
import com.app.ecom.orderservice.dto.order.response.OrderResponse;
import com.app.ecom.orderservice.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",  uses = OrderItemMapper.class)
public interface OrderMapper {
   OrderResponse toResponse(Order order);
}
