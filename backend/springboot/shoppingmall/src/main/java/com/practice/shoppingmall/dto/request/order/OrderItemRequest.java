package com.practice.shoppingmall.dto.request.order;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemRequest {
    @NotBlank(message = "상품 정보를 입력해주세요")
    private String uuid;

    @NotBlank(message = "구매 수량을 입력해주세요")
    private int count;
}