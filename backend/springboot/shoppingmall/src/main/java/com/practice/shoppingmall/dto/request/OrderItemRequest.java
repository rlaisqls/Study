package com.practice.shoppingmall.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Id;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class OrderItemRequest {
    private UUID uuid;
    private int count;
}