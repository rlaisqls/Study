package com.practice.shoppingmall.dto.response.order;

import com.practice.shoppingmall.dto.response.Response;
import com.practice.shoppingmall.entity.order.Order;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Builder
public class FindOrderGroupResponse implements Response {
    List<FindOrderResponse> orderResponseList;

    private final Integer totalPage;

    private final Long totalSize;

    public static FindOrderGroupResponse of(Page<Order> orderPage) {
        List<FindOrderResponse> orderResponseList = orderPage
                .map(FindOrderResponse::of).toList();

        return FindOrderGroupResponse.builder()
                .orderResponseList(orderResponseList)
                .totalPage(orderPage.getTotalPages())
                .totalSize(orderPage.getTotalElements())
                .build();
    }
}