package com.practice.shoppingmall.domain.order.domain.repository;

import com.practice.shoppingmall.domain.order.domain.Order;
import com.practice.shoppingmall.domain.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    Page<Order> findByUser(User user, Pageable pageable);
}