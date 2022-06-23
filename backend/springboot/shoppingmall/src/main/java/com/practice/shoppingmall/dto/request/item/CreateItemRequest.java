package com.practice.shoppingmall.dto.request.item;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class CreateItemRequest {
    @NotNull
    private String name;

    @NotNull
    private int price;

    @NotNull
    private int stock;
}