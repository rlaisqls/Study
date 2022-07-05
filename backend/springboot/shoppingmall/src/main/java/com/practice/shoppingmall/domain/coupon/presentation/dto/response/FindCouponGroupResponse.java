package com.practice.shoppingmall.domain.coupon.presentation.dto.response;

import com.practice.shoppingmall.domain.coupon.domain.Coupon;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;


@Getter
@Builder
public class FindCouponGroupResponse {
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