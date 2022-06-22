package com.practice.shoppingmall.entity.order;

import com.practice.shoppingmall.entity.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}