package com.practice.shoppingmall.domain.item.presentation.dto.request;

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
public class ModifyItemInfoRequest {
    @NotBlank(message = "상품 ID를 입력해주세요")
    private UUID itemUuid;

    @NotBlank(message = "수정할 이름을 입력해주세요")
    private String name;

    @NotBlank
    private int price;
}