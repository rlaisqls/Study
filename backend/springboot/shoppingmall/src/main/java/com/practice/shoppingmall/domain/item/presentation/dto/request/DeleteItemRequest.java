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
public class DeleteItemRequest {

    @NotNull(message = "item_id는 null을 허용하지 않습니다")
    private Long itemId;
}