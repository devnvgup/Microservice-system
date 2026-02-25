package com.example.order_service.dto;

import com.example.order_service.Enum.OrderStatus;

public class UpdateOrderStatusRequest {

    private OrderStatus status;

    // No-args constructor
    public UpdateOrderStatusRequest() {
    }

    // All-args constructor
    public UpdateOrderStatusRequest(OrderStatus status) {
        this.status = status;
    }

    // Getter
    public OrderStatus getStatus() {
        return status;
    }

    // Setter
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}