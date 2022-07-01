package com.practice.shoppingmall.dto.request.coupon;

import com.practice.shoppingmall.entity.coupon.CouponDiscountType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

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