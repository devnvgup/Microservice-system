package com.example.order_service.dto;

import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderRequest {
     private String userId;
     private List<CreateOrderItemRequest> items;
}
