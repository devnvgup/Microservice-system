package com.example.order_service.dto;

import java.math.BigDecimal;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderItemRequest {
    private String productId;

    private Integer quantity;

    private BigDecimal price;
}
