package com.example.order_service.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public abstract class BaseEntity {

  @Column(name = "created_at", updatable = false)
  protected LocalDateTime createdAt;

  @Column(name = "created_by", updatable = false)
  protected String createdBy;

  @Column(name = "updated_at")
  protected LocalDateTime updatedAt;

  @Column(name = "updated_by")
  protected String updatedBy;

  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = LocalDateTime.now();
  }
}
