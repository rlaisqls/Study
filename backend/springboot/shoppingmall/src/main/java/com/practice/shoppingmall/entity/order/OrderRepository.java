package com.practice.shoppingmall.entity.order;

import com.practice.shoppingmall.entity.item.Item;
import com.practice.shoppingmall.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    Page<Order> findByUser(User user, Pageable pageable);
}