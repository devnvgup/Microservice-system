package com.example.order_service.controller;

import com.example.order_service.dto.CreateOrderRequest;
import com.example.order_service.dto.OrderResponse;
import com.example.order_service.entity.OrderEntity;
import com.example.order_service.mapper.OrderMapper;
import com.example.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
  private final OrderService orderService;
  @PostMapping
  // create order
  public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
    OrderResponse order = orderService.createOrder(request);
    return ResponseEntity.ok(order);
  }

  // GET /orders/{orderId}
  @GetMapping("/{orderId}")
  public ResponseEntity<OrderResponse> getOrderById(
          @PathVariable Long orderId
  ) {
    OrderResponse order = orderService.getById(orderId);
    return ResponseEntity.ok(order);
  }

  // get user order
  // GET /orders?userId=u1
  @GetMapping
  public ResponseEntity<List<OrderResponse>> getOrdersByUser(
          @RequestParam String userId
  ) {
    List<OrderResponse> orders = orderService.getByUserId(userId);
    return ResponseEntity.ok(orders);
  }
  // cancel order
  // POST /orders/{orderId}/cancel
  @PostMapping("/{orderId}/cancel")
  public ResponseEntity<Void> cancelOrder(
          @PathVariable Long orderId,
          @RequestHeader("X-User-Id") String userId
  ) {
    orderService.cancelOrder(orderId, userId);
    return ResponseEntity.noContent().build();
  }
}