package com.practice.shoppingmall.domain.order.presentation.dto.response;

import com.practice.shoppingmall.domain.order.domain.Order;
import com.practice.shoppingmall.domain.order.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
                .map(FindOrderResponse::of)
                .collect(Collectors.toList()));
    }

    @Getter
    @Builder
    private static class FindOrderResponse {

        private Long orderId;

        private OrderStatus orderStatus;

        private String representativeItemName;

        private LocalDateTime orderDate;

        public static FindOrderResponse of(Order order){

            return FindOrderResponse
                    .builder()
                    .orderId(order.getId())
                    .representativeItemName(order.getOrderItems().get(0).getItem().getName())
                    .orderStatus(order.getOrderStatus())
                    .orderDate(order.getOrderDate())
                    .build();
        }
    }
}