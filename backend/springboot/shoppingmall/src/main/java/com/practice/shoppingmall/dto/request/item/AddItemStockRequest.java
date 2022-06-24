package com.practice.shoppingmall.dto.request.item;


import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
public class AddItemStockRequest {
    @NotNull(message = "상품 ID를 입력해주세요")
    private String itemUuid;

    @NotNull(message = "추가할 재고량을 입력해주세요")
    private int addItemStock;
}