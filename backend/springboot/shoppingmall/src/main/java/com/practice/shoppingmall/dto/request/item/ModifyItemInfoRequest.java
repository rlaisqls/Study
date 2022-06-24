package com.practice.shoppingmall.dto.request.item;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
public class ModifyItemInfoRequest {
    @NotBlank(message = "상품 ID를 입력해주세요")
    private String itemUuid;

    @NotBlank(message = "수정할 이름을 입력해주세요")
    private String name;

    private int price;
}