package com.practice.shoppingmall.domain.delivery.domain.repository;

import com.practice.shoppingmall.domain.delivery.domain.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeliveryRepository extends JpaRepository<Delivery, UUID> {
}