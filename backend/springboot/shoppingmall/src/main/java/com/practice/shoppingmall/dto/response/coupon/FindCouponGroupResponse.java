package com.practice.shoppingmall.dto.response.coupon;

import com.practice.shoppingmall.dto.response.Response;
import com.practice.shoppingmall.entity.coupon.Coupon;
import com.practice.shoppingmall.entity.coupon.CouponDiscountType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Getter
@Builder
public class FindCouponGroupResponse implements Response {
    private List<FindCouponResponse> CouponResponseList;

    public static FindCouponGroupResponse of(List<Coupon> coupons) {

        List<FindCouponResponse> findCouponResponses = coupons
                .stream()
                .map(FindCouponResponse::of)
                .collect(Collectors.toList());

        return FindCouponGroupResponse
                .builder()
                .CouponResponseList(findCouponResponses)
                .build();
    }
}