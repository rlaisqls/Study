package com.practice.shoppingmall.dto.response.item;

import com.practice.shoppingmall.dto.response.Response;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class FindItemGroupResponse implements Response {
    List<FindItemResponse> itemResponseList;

    private final Integer totalPage;

    private final Long totalSize;
}