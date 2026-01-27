package com.example.order_service.service.Impl;

import com.example.order_service.Enum.OrderStatus;
import com.example.order_service.dto.CreateOrderItemRequest;
import com.example.order_service.dto.CreateOrderRequest;
import com.example.order_service.dto.OrderResponse;
import com.example.order_service.entity.OrderEntity;
import com.example.order_service.entity.OrderItemEntity;
import com.example.order_service.kafka.OrderCreatedEvent;
import com.example.order_service.kafka.producer.OrderEventProducer;
import com.example.order_service.mapper.OrderMapper;
import com.example.order_service.repository.OrderRepository;
import com.example.order_service.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderEventProducer orderEventProducer;
    private final ApplicationEventPublisher eventPublisher;
    @Override
    public OrderResponse createOrder(CreateOrderRequest request) {
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
        order = orderRepository.save(order);
        // not send kafka in here, event publish local, only send when transaction commit
        OrderCreatedEvent event = new OrderCreatedEvent(
                order.getId(),
                order.getUserId(),
                order.getTotalAmount()
        );

        eventPublisher.publishEvent(event);

        orderEventProducer.publishOrderCreated(event);
       return OrderMapper.toResponse(order);
    }

    @Override
    public OrderResponse getById(Long orderId) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return OrderMapper.toResponse(order);
    }

    @Override
    public List<OrderResponse> getByUserId(String userId) {
        List<OrderEntity> orders = orderRepository.findByUserId(userId);
        if (orders.isEmpty()) {
            throw new RuntimeException("No orders found for userId: " + userId);
        }
        return OrderMapper.toResponseList(orders);
    }

    @Override
    public void cancelOrder(Long orderId, String userId) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!order.getUserId().equals(userId)) {
            throw new RuntimeException("Forbidden");
        }

        if (!order.getStatus().equals(OrderStatus.CREATED)) {
            throw new RuntimeException("Order cannot be cancelled");
        }

        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);

    }


    @Override
    public void updateStatus (Long orderId, OrderStatus status) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        orderRepository.save(order);
    }

}
