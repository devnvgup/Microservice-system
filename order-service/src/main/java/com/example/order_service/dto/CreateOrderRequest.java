package com.example.order_service.dto;

import java.util.List;

public class CreateOrderRequest {

    private String userId;
    private List<CreateOrderItemRequest> items;

    // No-args constructor
    public CreateOrderRequest() {
    }

    // All-args constructor
    public CreateOrderRequest(String userId, List<CreateOrderItemRequest> items) {
        this.userId = userId;
        this.items = items;
    }

    // Getter & Setter
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<CreateOrderItemRequest> getItems() {
        return items;
    }

    public void setItems(List<CreateOrderItemRequest> items) {
        this.items = items;
    }

    // ===== Builder pattern =====
    public static class Builder {
        private String userId;
        private List<CreateOrderItemRequest> items;

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder items(List<CreateOrderItemRequest> items) {
            this.items = items;
            return this;
        }

        public CreateOrderRequest build() {
            return new CreateOrderRequest(userId, items);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}