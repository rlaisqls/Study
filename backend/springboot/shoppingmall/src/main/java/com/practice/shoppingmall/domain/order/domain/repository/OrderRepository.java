package com.practice.shoppingmall.domain.order.domain.repository;

import com.practice.shoppingmall.domain.order.domain.Order;
import com.practice.shoppingmall.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByUser(User user);
}