package com.practice.shoppingmall.domain.order.presentation.dto.response;

import com.practice.shoppingmall.domain.order.domain.Order;
import com.practice.shoppingmall.domain.order.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class QueryOrderListResponse {
    List<FindOrderResponse> orderListResponse;

    public static QueryOrderListResponse of(List<Order> orders) {

        return new QueryOrderListResponse(orders
                .stream()
                .map(FindOrderResponse::of)
                .collect(Collectors.toList()));
    }

    @Getter
    @Builder
    private static class FindOrderResponse {

        private Long orderId;

        private OrderStatus orderStatus;

        private LocalDate orderDate;

        private String representativeItemName;

        private int totalItemCount;

        public static FindOrderResponse of(Order order){

            return FindOrderResponse
                    .builder()
                    .orderId(order.getId())
                    .representativeItemName(order.getOrderItems().get(0).getItem().getName())
                    .totalItemCount(order.getOrderItems().size())
                    .orderStatus(order.getOrderStatus())
                    .orderDate(order.getOrderDateTime().toLocalDate())
                    .build();
        }
    }
}