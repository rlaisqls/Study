package com.practice.shoppingmall.dto.response.item;

import com.practice.shoppingmall.dto.response.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class CreateItemResponse implements Response {
    UUID itemUuid;
}