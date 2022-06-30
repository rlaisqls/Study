package com.practice.shoppingmall.dto.response.item;

import com.practice.shoppingmall.dto.response.Response;
import com.practice.shoppingmall.entity.item.Item;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Builder
public class FindItemGroupResponse implements Response {
    List<FindItemResponse> itemResponseList;

    private final Integer totalPage;

    private final Long totalSize;

    public static FindItemGroupResponse of(Page<Item> itemPage) {

        List<FindItemResponse> itemResponseList = itemPage
                .map(FindItemResponse::of).toList();

        return FindItemGroupResponse
                .builder()
                .itemResponseList(itemResponseList)
                .totalPage(itemPage.getTotalPages())
                .totalSize(itemPage.getTotalElements()).build();
    }
}