package com.practice.shoppingmall.domain.order.presentation.dto.response;

import com.practice.shoppingmall.domain.order.domain.Order;
import com.practice.shoppingmall.domain.order.domain.OrderItem;
import com.practice.shoppingmall.domain.order.domain.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class QueryOrderInfoResponse {

    private long orderId;

    private OrderStatus orderStatus;

    private LocalDateTime orderDateTime;

    private List<OrderItemResponse> orderItems;

    private long deliveryId;

    private int totalPrice;

    public static QueryOrderInfoResponse of(Order order) {

        List<OrderItemResponse> orderItemList = order.getOrderItems()
                .stream()
                .map(OrderItemResponse::of)
                .collect(Collectors.toList());

        return QueryOrderInfoResponse
                .builder()
                .orderId(order.getId())
                .orderStatus(order.getOrderStatus())
                .orderDateTime(order.getOrderDateTime())
                .orderItems(orderItemList)
                .deliveryId(order.getDelivery().getId())
                .totalPrice(order.getTotalPrice())
                .build();
    }

    @Getter
    @Builder
    private static class OrderItemResponse {
        private String itemName;
        private int count;

        public static OrderItemResponse of(OrderItem orderItem) {

            return OrderItemResponse.builder()
                    .itemName(orderItem.getItem().getName())
                    .count(orderItem.getCount())
                    .build();
        }
    }
}