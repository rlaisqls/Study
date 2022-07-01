package com.practice.shoppingmall.service.order;

import com.practice.shoppingmall.dto.request.order.OrderItemRequest;
import com.practice.shoppingmall.dto.request.order.OrderRequest;
import com.practice.shoppingmall.dto.response.order.CreateOrderResponse;
import com.practice.shoppingmall.dto.response.order.FindOrderGroupResponse;
import com.practice.shoppingmall.dto.response.order.FindOrderResponse;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    CreateOrderResponse doOrder(OrderRequest request);

    void cancelOrder(String id);

    FindOrderResponse findOneOrder(UUID id);

    FindOrderGroupResponse findMyOrder(int page, int size);
}