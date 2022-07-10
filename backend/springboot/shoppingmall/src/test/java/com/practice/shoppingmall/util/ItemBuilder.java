package com.practice.shoppingmall.util;

import com.practice.shoppingmall.constant.ItemConstant;
import com.practice.shoppingmall.domain.item.domain.Item;

public class ItemBuilder {
    public static Item build() {
        return Item
                .builder()
                .id(1L)
                .name(ItemConstant.NAME)
                .price(ItemConstant.PRICE)
                .stock(ItemConstant.STOCK)
                .build();
    }
}