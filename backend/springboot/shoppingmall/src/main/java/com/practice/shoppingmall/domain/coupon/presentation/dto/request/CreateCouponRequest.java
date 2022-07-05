package com.practice.shoppingmall.domain.coupon.presentation.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateCouponRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String discountType;

    //
    @Nullable
    private Integer discountAmount;

    @Nullable
    private Integer discountRate;
    //

    @NotBlank
    private Integer expirationTime;
}