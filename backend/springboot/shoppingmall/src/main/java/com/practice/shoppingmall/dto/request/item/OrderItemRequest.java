package com.practice.shoppingmall.dto.request.item;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class OrderItemRequest {
    @NotNull
    private UUID uuid;

    @NotNull
    private int count;
}