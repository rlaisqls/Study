package com.practice.shoppingmall.domain.order.presentation.dto.response;

import com.practice.shoppingmall.domain.delivery.domain.Delivery;
import com.practice.shoppingmall.domain.item.presentation.dto.response.OrderItemResponse;
import com.practice.shoppingmall.domain.order.domain.Order;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class FindOrderInfoResponse {

    private String orderStatus;

    private String orderDate;

    private List<OrderItemResponse> orderItems;

    private Delivery delivery;

    private int totalPrice;

    public static FindOrderInfoResponse of(Order order) {

        List<OrderItemResponse> orderItemList = order.getOrderItems()
                .stream()
                .map(OrderItemResponse::of)
                .collect(Collectors.toList());

        return FindOrderInfoResponse
                .builder()
                .orderStatus(order.getOrderStatus().toString())
                .orderDate(order.getOrderDate().toString())
                .orderItems(orderItemList)
                .totalPrice(order.getTotalPrice())
                .build();
    }
}