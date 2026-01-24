package com.example.order_service.dto;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.order_service.Enum.OrderStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    private Long orderId;

    private String userId;

    private OrderStatus status;

    private BigDecimal totalAmount;

    private LocalDateTime createdAt;

    private List<OrderItemResponse> items;
}
