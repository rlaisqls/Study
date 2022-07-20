package com.practice.shoppingmall.domain.item.presentation.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class AddItemStockRequest {

    @NotNull(message = "addStock은 null을 허용ㅇ하지 않습니다")
    private Integer addStock;
}