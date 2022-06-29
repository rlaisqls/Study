package com.practice.shoppingmall.dto.response.order;

import com.practice.shoppingmall.dto.response.Response;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class FindOrderGroupResponse implements Response {
    List<FindOrderResponse> orderResponseList;

    private final Integer totalPage;

    private final Long totalSize;
}