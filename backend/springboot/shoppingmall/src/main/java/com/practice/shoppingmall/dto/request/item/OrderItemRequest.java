package com.practice.shoppingmall.dto.request.item;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class OrderItemRequest {
    @NotBlank(message = "상품 정보를 입력해주세요")
    private String uuid;

    @NotBlank(message = "구매 수량을 입력해주세요")
    private int count;
}