package com.practice.shoppingmall.dto.response.order;

import com.practice.shoppingmall.dto.response.Response;
import com.practice.shoppingmall.dto.response.item.OrderItemResponse;
import com.practice.shoppingmall.entity.order.Order;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class FindOrderResponse implements Response {
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
                .orderStatus(order.getStatus().toString())
                .orderDate(order.getOrderDate().toString())
                .totalPrice(order.getTotalPrice())
                .build();
    }
}