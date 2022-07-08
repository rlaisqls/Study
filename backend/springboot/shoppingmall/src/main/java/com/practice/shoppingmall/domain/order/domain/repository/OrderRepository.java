package com.practice.shoppingmall.domain.order.domain.repository;

import com.practice.shoppingmall.domain.order.domain.Order;
import com.practice.shoppingmall.domain.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {
    Page<Order> findByUser(User user, Pageable pageable);
}