package com.practice.shoppingmall.dto.request.item;


import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
public class AddItemStockRequest {
    @NotNull
    private UUID itemUuid;

    @NotNull
    private int addItemStock;
}