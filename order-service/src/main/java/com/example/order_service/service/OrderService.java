package com.example.order_service.service;

import com.example.order_service.Enum.OrderStatus;
import com.example.order_service.dto.CreateOrderRequest;
import com.example.order_service.dto.OrderResponse;
import com.example.order_service.entity.OrderEntity;

import java.util.List;

public  interface OrderService {
    OrderResponse createOrder(CreateOrderRequest request);
    OrderResponse getById(Long orderId);
    List<OrderResponse> getByUserId(String userId);
    void cancelOrder(Long orderId, String userId);
    void updateStatus(Long orderId, OrderStatus status);
}
