package com.practice.shoppingmall.entity.delivery;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeliveryRepository extends JpaRepository<Delivery, UUID> {
}