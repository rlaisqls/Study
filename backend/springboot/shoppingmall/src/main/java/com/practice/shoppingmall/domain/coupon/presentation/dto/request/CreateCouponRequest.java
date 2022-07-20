package com.practice.shoppingmall.domain.coupon.presentation.dto.request;

import com.practice.shoppingmall.domain.coupon.domain.CouponDiscountType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CreateCouponRequest {

    @NotBlank(message = "couponName는 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    @Length(min = 1, max = 30, message = "couponName는 30자 이하여야 합니다.")
    private String couponName;

    @NotNull(message = "discountType는 null을 허용하지 않습니다")
    private CouponDiscountType discountType;

    @NotNull(message = "discountAmount는 null을 허용하지 않습니다")
    private Integer discountAmount;

    @NotNull(message = "validityPeriod는 null을 허용하지 않습니다")
    private Integer validityPeriod;
}