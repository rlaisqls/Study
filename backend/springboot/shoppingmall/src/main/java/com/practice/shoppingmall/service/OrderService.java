package com.practice.shoppingmall.service;

import com.practice.shoppingmall.dto.request.order.OrderItemRequest;
import com.practice.shoppingmall.dto.request.order.OrderRequest;
import com.practice.shoppingmall.dto.response.order.CreateOrderResponse;
import com.practice.shoppingmall.dto.response.order.FindOrderGroupResponse;
import com.practice.shoppingmall.dto.response.order.FindOrderResponse;

import java.util.List;

public interface OrderService {

    CreateOrderResponse order(OrderRequest request);

    void cancelOrder(String uuid);

    FindOrderResponse findOrder(String uuid);

    FindOrderGroupResponse findMyOrder(int page, int size);
}