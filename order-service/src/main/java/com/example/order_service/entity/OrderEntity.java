package com.example.order_service.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.order_service.Enum.OrderStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, length = 50)
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private OrderStatus status;

    @Column(name = "total_amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalAmount;

    @OneToMany(
        mappedBy = "order",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    private List<OrderItemEntity> items = new ArrayList<>();

    // ===== No-args constructor (BẮT BUỘC cho JPA) =====
    public OrderEntity() {
    }

    // ===== All-args constructor =====
    public OrderEntity(Long id, String userId, OrderStatus status,
                       BigDecimal totalAmount, List<OrderItemEntity> items) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.totalAmount = totalAmount;
        this.items = items;
    }

    // ===== Getter & Setter =====

    public Long getId() {
        return id;
    }

    // Thường không nên public setId nếu ID auto generate
    // public void setId(Long id) {
    //     this.id = id;
    // }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<OrderItemEntity> getItems() {
        return items;
    }

    public void setItems(List<OrderItemEntity> items) {
        this.items = items;
    }

    // ===== Helper method cho quan hệ 2 chiều =====
    public void addItem(OrderItemEntity item) {
        items.add(item);
        item.setOrder(this);
    }

    public void removeItem(OrderItemEntity item) {
        items.remove(item);
        item.setOrder(null);
    }
}