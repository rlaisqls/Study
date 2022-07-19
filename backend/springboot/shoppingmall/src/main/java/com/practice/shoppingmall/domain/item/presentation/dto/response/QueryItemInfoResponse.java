package com.practice.shoppingmall.domain.item.presentation.dto.response;

import com.practice.shoppingmall.domain.item.domain.Item;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QueryItemInfoResponse {

    private Long itemId;

    private String itemName;

    private int price;

    private int stock;

    private Boolean isSoldOut;

    public static QueryItemInfoResponse of (Item item) {

        return QueryItemInfoResponse
                .builder()
                .itemId(item.getId())
                .itemName(item.getName())
                .price(item.getPrice())
                .stock(item.getStock())
                .isSoldOut(item.getStock() == 0)
                .build();
    }
}