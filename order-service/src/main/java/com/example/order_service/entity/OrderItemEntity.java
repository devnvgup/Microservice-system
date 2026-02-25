package com.example.order_service.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "order_item")
public class OrderItemEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @Column(name = "product_id", nullable = false, length = 50)
    private String productId;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    // ===== Constructor rỗng (BẮT BUỘC cho JPA) =====
    public OrderItemEntity() {
    }

    // ===== All-args constructor =====
    public OrderItemEntity(Long id, OrderEntity order, String productId,
                           Integer quantity, BigDecimal price) {
        this.id = id;
        this.order = order;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    // ===== Getter & Setter =====

    public Long getId() {
        return id;
    }

    // Thường không nên public setId nếu auto generate
    // public void setId(Long id) {
    //     this.id = id;
    // }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

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
}