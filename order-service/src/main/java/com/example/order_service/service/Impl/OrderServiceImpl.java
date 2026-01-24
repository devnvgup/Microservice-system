package com.example.order_service.service.Impl;

import com.example.order_service.Enum.OrderStatus;
import com.example.order_service.dto.CreateOrderItemRequest;
import com.example.order_service.dto.CreateOrderRequest;
import com.example.order_service.entity.OrderEntity;
import com.example.order_service.entity.OrderItemEntity;
import com.example.order_service.repository.OrderRepository;
import com.example.order_service.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    @Override
    public OrderEntity createOrder(CreateOrderRequest request) {
        OrderEntity order = new OrderEntity();
        order.setUserId(request.getUserId());
        order.setStatus(OrderStatus.CREATED);

        BigDecimal total = BigDecimal.ZERO;

        for (CreateOrderItemRequest itemReq : request.getItems()) {
            OrderItemEntity item = new OrderItemEntity();
            item.setProductId(itemReq.getProductId());
            item.setPrice(itemReq.getPrice());
            item.setQuantity(itemReq.getQuantity());

            BigDecimal itemTotal =
                    itemReq.getPrice().multiply(BigDecimal.valueOf(itemReq.getQuantity()));

            total = total.add(itemTotal);
            order.addItem(item);
        }
        order.setTotalAmount(total);
       return orderRepository.save(order);
    }

}
