package com.practice.shoppingmall.dto.response;

import com.practice.shoppingmall.dto.response.item.OrderItemResponse;
import com.practice.shoppingmall.entity.order.OrderItem;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.index.GeoIndexed;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class OrderResponse {
    private String username;
    private String orderStatus;
    private List<OrderItemResponse> orderItemList;
}