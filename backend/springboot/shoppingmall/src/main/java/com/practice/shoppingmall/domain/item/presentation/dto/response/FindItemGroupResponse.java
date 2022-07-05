package com.practice.shoppingmall.domain.item.presentation.dto.response;

import com.practice.shoppingmall.domain.item.domain.Item;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Builder
public class FindItemGroupResponse {
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