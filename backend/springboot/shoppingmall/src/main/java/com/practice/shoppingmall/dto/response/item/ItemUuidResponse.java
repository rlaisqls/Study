package com.practice.shoppingmall.dto.response.item;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ItemUuidResponse {
    UUID itemUuid;
}