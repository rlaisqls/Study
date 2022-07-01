package com.practice.shoppingmall.dto.request.item;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddItemStockRequest {
    @NotBlank(message = "상품 ID를 입력해주세요")
    private UUID itemUuid;

    @NotBlank(message = "추가할 재고량을 입력해주세요")
    private int addItemStock;
}