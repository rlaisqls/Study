package com.practice.shoppingmall.dto.request.item;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
public class ModifyItemInfoRequest {
    @NotNull
    private UUID itemUuid;

    private String name;

    private int price;
}