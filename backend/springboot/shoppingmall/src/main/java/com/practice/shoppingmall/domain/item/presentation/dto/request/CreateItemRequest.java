package com.practice.shoppingmall.domain.item.presentation.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateItemRequest {
    @NotBlank(message = "이름을 입력해주세요")
    private String name;

    @NotNull(message = "가격을 입력해주세요")
    private int price;

    @NotNull(message = "재고량을 입력해주세요")
    private int stock;
}