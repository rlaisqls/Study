package com.practice.shoppingmall.domain.item.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CreateItemRequest {

    @NotEmpty(message = "item_name는 Null 또는 공백을 허용하지 않습니다.")
    @Length(min = 1, max = 30, message = "itemName는 30자 이하여야 합니다.")
    private String itemName;

    @NotNull(message = "price는 null을 허용하지 않습니다")
    private int price;

    @NotNull(message = "stock은 null을 허용하지 않습니다")
    private int stock;
}