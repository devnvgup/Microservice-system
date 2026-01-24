package com.example.order_service.service;

import com.example.order_service.dto.CreateOrderRequest;
import com.example.order_service.entity.OrderEntity;

public  interface OrderService {
    OrderEntity createOrder(CreateOrderRequest request);
}
