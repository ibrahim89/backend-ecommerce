package com.ecommerce.user_service.entity;


import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class Auditable {
    @PrePersist
    public void onCreate(Object entity) {
        if (entity instanceof User user) {
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());
            user.setIsActive(true); // default on creation
        }
    }

    @PreUpdate
    public void onUpdate(Object entity) {
        if (entity instanceof User user) {
            user.setUpdatedAt(LocalDateTime.now());
        }
    }
}
