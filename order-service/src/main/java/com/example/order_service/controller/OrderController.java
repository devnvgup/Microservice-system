package com.example.order_service.controller;

import com.example.order_service.dto.CreateOrderRequest;
import com.example.order_service.dto.OrderResponse;
import com.example.order_service.entity.OrderEntity;
import com.example.order_service.mapper.OrderMapper;
import com.example.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
  private final OrderService orderService;
  @PostMapping
  public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
    OrderEntity order = orderService.createOrder(request);
    return ResponseEntity.ok(OrderMapper.toResponse(order));
  }
}