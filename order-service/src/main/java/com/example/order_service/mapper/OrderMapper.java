package com.example.order_service.mapper;

import java.util.stream.Collectors;

import com.example.order_service.dto.OrderItemResponse;
import com.example.order_service.dto.OrderResponse;
import com.example.order_service.entity.OrderEntity;

public class OrderMapper {
    public static OrderResponse toResponse(OrderEntity order) {
        return OrderResponse.builder()
                .orderId(order.getId())
                .userId(order.getUserId())
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .createdAt(order.getCreatedAt())
                .items(
                        order.getItems()
                                .stream()
                                .map(item -> OrderItemResponse.builder()
                                        .productId(item.getProductId())
                                        .quantity(item.getQuantity())
                                        .price(item.getPrice())
                                        .build()
                                )
                                .collect(Collectors.toList())
                )
                .build();
    }
}
