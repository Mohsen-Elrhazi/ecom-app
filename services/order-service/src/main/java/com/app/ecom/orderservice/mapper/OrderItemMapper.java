package com.app.ecom.orderservice.mapper;

import com.app.ecom.orderservice.dto.orderItem.response.OrderItemResponse;
import com.app.ecom.orderservice.entity.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    OrderItemResponse toResponse(OrderItem orderItem);
}
