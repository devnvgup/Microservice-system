package com.example.order_service.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UpdateOrderStatusRequest {
    private String status;
}
