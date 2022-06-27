package com.practice.shoppingmall.dto.response.item;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class findItemResponse {
    String uuid;
    String name;
    int price;
    Boolean isSoldOut;
}