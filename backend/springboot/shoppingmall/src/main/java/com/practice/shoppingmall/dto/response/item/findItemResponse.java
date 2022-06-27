package com.practice.shoppingmall.dto.response.item;

import com.practice.shoppingmall.dto.response.Response;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FindItemResponse implements Response {
    String uuid;
    String name;
    int price;
    int stock;
    Boolean isSoldOut;
}