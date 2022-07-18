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
public class FindItemRequest {

    @NotNull(message = "상품 ID를 입력해주세요")
    private Long itemId;
}