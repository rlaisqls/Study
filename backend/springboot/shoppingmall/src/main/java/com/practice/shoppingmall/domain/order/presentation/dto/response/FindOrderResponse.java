package com.practice.shoppingmall.domain.order.presentation.dto.response;

import com.practice.shoppingmall.domain.item.presentation.dto.response.OrderItemResponse;
import com.practice.shoppingmall.domain.order.domain.Order;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class FindOrderResponse {
    private String username;
    private String orderStatus;
    private String orderDate;
    private List<OrderItemResponse> orderItems;
    private int totalPrice;

    public static FindOrderResponse of(Order order) {

        List<OrderItemResponse> orderItemList = order.getOrderItems()
                .stream().map(OrderItemResponse::of).collect(Collectors.toList());

        return FindOrderResponse
                .builder()
                .orderItems(orderItemList)
                .orderStatus(order.getOrderStatus().toString())
                .orderDate(order.getOrderDate().toString())
                .totalPrice(order.getTotalPrice())
                .build();
    }
}