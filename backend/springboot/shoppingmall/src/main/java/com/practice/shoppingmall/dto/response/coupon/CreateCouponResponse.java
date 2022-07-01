package com.practice.shoppingmall.dto.response.coupon;

import com.practice.shoppingmall.dto.response.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;


@Getter
@AllArgsConstructor
public class CreateCouponResponse implements Response {
    String uuid;
}