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
public class ModifyItemRequest {
    @NotNull(message = "상품 ID를 입력해주세요")
    private Long itemId;

    @NotNull(message = "수정할 이름을 입력해주세요")
    private String itemName;

    @NotNull
    private int price;
}