package com.practice.shoppingmall.domain.order.presentation.dto.response;

import com.practice.shoppingmall.domain.item.presentation.dto.response.OrderItemResponse;
import com.practice.shoppingmall.domain.order.domain.Order;
import com.practice.shoppingmall.domain.order.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class FindOrderGroupResponse {
    List<FindOrderResponse> orderResponseList;

    private final Integer totalPage;

    private final Long totalSize;

    public static FindOrderGroupResponse of(Page<Order> orderPage) {

        List<FindOrderResponse> orderResponseList = orderPage
                .map(FindOrderResponse::new)
                .toList();

        return FindOrderGroupResponse.builder()
                .orderResponseList(orderResponseList)
                .totalPage(orderPage.getTotalPages())
                .totalSize(orderPage.getTotalElements())
                .build();
    }

    @AllArgsConstructor
    private static class FindOrderResponse {
        private Long orderId;

        private OrderStatus orderStatus;

        private LocalDateTime orderDate;

        private List<OrderItemResponse> orderItems;

        private int totalPrice;

        FindOrderResponse(Order order){

            List<OrderItemResponse> orderItemList = order.getOrderItems()
                    .stream().map(OrderItemResponse::of).collect(Collectors.toList());

            this.orderStatus = order.getOrderStatus();
            this.orderDate = order.getOrderDate();
            this.orderItems = orderItemList;
            this.totalPrice = order.getTotalPrice();
        }
    }
}