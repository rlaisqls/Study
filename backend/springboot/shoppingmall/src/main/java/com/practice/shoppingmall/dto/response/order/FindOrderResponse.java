package com.practice.shoppingmall.dto.response.order;

import com.practice.shoppingmall.dto.response.Response;
import com.practice.shoppingmall.dto.response.item.OrderItemResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class FindOrderResponse implements Response {
    private String username;
    private String orderStatus;
    private List<OrderItemResponse> orderItemList;
}