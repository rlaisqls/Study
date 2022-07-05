package com.practice.shoppingmall.domain.order.service;

import com.practice.shoppingmall.domain.order.presentation.dto.request.OrderRequest;
import com.practice.shoppingmall.domain.order.presentation.dto.response.CreateOrderResponse;
import com.practice.shoppingmall.domain.order.presentation.dto.response.FindOrderGroupResponse;
import com.practice.shoppingmall.domain.order.presentation.dto.response.FindOrderResponse;

import java.util.UUID;

public interface OrderService {

    CreateOrderResponse doOrder(OrderRequest request);

    void cancelOrder(String id);

    FindOrderResponse findOneOrder(UUID id);

    FindOrderGroupResponse findMyOrder(int page, int size);
}