package com.example.order_service.controller;


import com.example.order_service.dto.UpdateOrderStatusRequest;
import com.example.order_service.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/internal/orders")
public class InternalOrderController {
    private final OrderService orderService;
    
    public InternalOrderController (OrderService orderService) {
        this.orderService = orderService;
    }
    // PUT /internal/orders/{orderId}/status
    @PutMapping("/{orderId}/status")
    public ResponseEntity<Void> updateOrderStatus(
            @PathVariable Long orderId,
            @RequestBody UpdateOrderStatusRequest request
    ) {
        orderService.updateStatus(orderId, request.getStatus());
        return ResponseEntity.noContent().build();
    }
}
