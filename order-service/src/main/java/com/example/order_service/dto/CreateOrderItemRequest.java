package com.example.order_service.dto;

import java.math.BigDecimal;

public class CreateOrderItemRequest {

    private String productId;
    private Integer quantity;
    private BigDecimal price;

    // No-args constructor
    public CreateOrderItemRequest() {
    }

    // All-args constructor
    public CreateOrderItemRequest(String productId, Integer quantity, BigDecimal price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    // Getter & Setter
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public static class Builder {
        private String productId;
        private Integer quantity;
        private BigDecimal price;

        public Builder productId(String productId) {
            this.productId = productId;
            return this;
        }

        public Builder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public CreateOrderItemRequest build() {
            return new CreateOrderItemRequest(productId, quantity, price);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}