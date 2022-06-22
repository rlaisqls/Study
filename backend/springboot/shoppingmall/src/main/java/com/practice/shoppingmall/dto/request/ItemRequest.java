package com.practice.shoppingmall.dto.request;

import lombok.Getter;

@Getter
public class ItemRequest {
    private String name;

    private int price;

    private int stock;
}