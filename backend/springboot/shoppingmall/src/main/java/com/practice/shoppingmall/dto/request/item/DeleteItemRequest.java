package com.practice.shoppingmall.dto.request.item;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
public class DeleteItemRequest {

    @NotBlank(message = "상품 ID를 입력해주세요")
    private String itemUuid;
}