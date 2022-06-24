package com.practice.shoppingmall.dto.request.item;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class CreateItemRequest {
    @NotNull(message = "이름을 입력해주세요")
    private String name;

    @NotNull(message = "가격을 입력해주세요")
    private int price;

    @NotNull(message = "재고량을 입력해주세요")
    private int stock;
}