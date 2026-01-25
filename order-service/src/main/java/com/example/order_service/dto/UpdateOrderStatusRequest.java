package com.example.order_service.dto;
import com.example.order_service.Enum.OrderStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UpdateOrderStatusRequest {
    private OrderStatus status;
}
