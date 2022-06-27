package com.practice.shoppingmall.service;

import com.practice.shoppingmall.dto.request.item.OrderItemRequest;
import com.practice.shoppingmall.dto.response.order.CreateOrderResponse;
import com.practice.shoppingmall.dto.response.order.FindOrderResponse;

import java.util.List;

public interface OrderService {

    CreateOrderResponse order(List<OrderItemRequest> itemsRequest);

    FindOrderResponse findOrder(String uuid);
}