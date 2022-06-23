package com.practice.shoppingmall.dto.request.item;

import lombok.Getter;

import java.util.UUID;

@Getter
public class DeleteItemRequest {
    private UUID itemUuid;
}