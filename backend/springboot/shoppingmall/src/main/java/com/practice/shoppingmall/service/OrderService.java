package com.practice.shoppingmall.service;

import com.practice.shoppingmall.dto.request.item.OrderItemRequest;
import com.practice.shoppingmall.dto.response.OrderResponse;
import com.practice.shoppingmall.entity.delivery.Delivery;
import com.practice.shoppingmall.entity.user.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderService {

    void order(List<OrderItemRequest> itemsRequest);

    OrderResponse findOrder(String uuid);
}