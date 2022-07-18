package com.practice.shoppingmall.domain.item.presentation.dto.request;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddItemStockRequest {

    @NotNull(message = "추가할 재고량을 입력해주세요")
    private int addStock;
}