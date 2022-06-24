package com.practice.shoppingmall.dto.request.item;


import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public class ItemFindRequest {
    private String uuid;
}