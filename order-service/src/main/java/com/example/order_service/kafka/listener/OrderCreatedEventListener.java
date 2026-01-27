package com.example.order_service.kafka.listener;

import com.example.order_service.kafka.OrderCreatedEvent;
import com.example.order_service.kafka.producer.OrderEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class OrderCreatedEventListener {
    private final OrderEventProducer orderEventProducer;

    @TransactionalEventListener(
            phase = TransactionPhase.AFTER_COMMIT
    )

    public void handle(OrderCreatedEvent orderCreatedEvent) {

        orderEventProducer.publishOrderCreated(orderCreatedEvent);
    }
}
