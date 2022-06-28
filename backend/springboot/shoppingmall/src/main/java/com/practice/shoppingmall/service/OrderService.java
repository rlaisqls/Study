package com.practice.shoppingmall.service;

import com.practice.shoppingmall.dto.request.item.OrderItemRequest;
import com.practice.shoppingmall.dto.response.order.CreateOrderResponse;
import com.practice.shoppingmall.dto.response.order.FindOrderListResponse;
import com.practice.shoppingmall.dto.response.order.FindOrderResponse;

import java.util.List;

public interface OrderService {

    CreateOrderResponse order(List<OrderItemRequest> itemsRequest);

    void cancelOrder(String uuid);

    FindOrderResponse findOrder(String uuid);

    FindOrderListResponse findMyOrder(int page, int size);
}