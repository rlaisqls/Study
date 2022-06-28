package com.practice.shoppingmall.dto.response.item;

import com.practice.shoppingmall.dto.response.Response;
import com.practice.shoppingmall.entity.item.Item;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FindItemResponse implements Response {

    private String uuid;

    private String name;

    private Integer price;

    private Integer stock;

    private Boolean isSoldOut;

    public static FindItemResponse of(Item item) {
        return FindItemResponse
                .builder()
                .uuid(item.getId().toString())
                .name(item.getName())
                .price(item.getPrice())
                .stock(item.getStock())
                .isSoldOut(item.getStock()==0)
                .build();
    }
}