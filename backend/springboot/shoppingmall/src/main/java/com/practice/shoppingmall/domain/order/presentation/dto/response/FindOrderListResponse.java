package com.practice.shoppingmall.domain.order.presentation.dto.response;

import com.practice.shoppingmall.domain.order.domain.Order;
import com.practice.shoppingmall.domain.order.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class FindOrderListResponse {
    List<FindOrderResponse> orderListResponse;

    public static FindOrderListResponse of(List<Order> orders) {

        return new FindOrderListResponse(orders
                .stream()
                .map(FindOrderResponse::new)
                .collect(Collectors.toList()));

    }

    @AllArgsConstructor
    private static class FindOrderResponse {
        private Long orderId;

        private OrderStatus orderStatus;

        private String representativeItemName;

        private LocalDateTime orderDate;

        FindOrderResponse(Order order){

            this.orderId = order.getId();
            this.representativeItemName = order.getOrderItems().get(0).getItem().getName();
            this.orderStatus = order.getOrderStatus();
            this.orderDate = order.getOrderDate();
        }
    }
}