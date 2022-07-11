package com.practice.shoppingmall.util;

import com.practice.shoppingmall.constant.CouponConstant;
import com.practice.shoppingmall.domain.coupon.domain.Coupon;
import com.practice.shoppingmall.domain.coupon.domain.CouponDiscountType;
import com.practice.shoppingmall.domain.coupon.domain.UserCoupon;

public class CouponBuilder {

    public static UserCoupon userCouponBuild(Coupon coupon) {
        return UserCoupon
                .builder()
                .id(CouponConstant.USER_COUPON_ID)
                .user(UserBuilder.build())
                .coupon(coupon)
                .expirationDate(CouponConstant.LOCAL_DATE_TIME_NOW.plusMinutes(CouponConstant.VALIDITY_PERIOD))
                .build();
    }

    public static Coupon fixDiscountCouponBuild(int discountPrice) {
        return Coupon
                .builder()
                .id(CouponConstant.ID)
                .validityPeriod(CouponConstant.VALIDITY_PERIOD)
                .couponName(CouponConstant.NAME)
                .discountType(CouponDiscountType.FIXED)
                .discountPrice(discountPrice)
                .build();
    }

    public static Coupon rateDiscountCouponBuild() {
        return Coupon
                .builder()
                .id(1L)
                .validityPeriod(CouponConstant.VALIDITY_PERIOD)
                .couponName(CouponConstant.NAME)
                .discountType(CouponDiscountType.RATE)
                .discountRate(10)
                .build();
    }

}