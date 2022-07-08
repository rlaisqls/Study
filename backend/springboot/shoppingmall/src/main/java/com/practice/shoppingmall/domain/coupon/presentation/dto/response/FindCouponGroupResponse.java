package com.practice.shoppingmall.domain.coupon.presentation.dto.response;

import com.practice.shoppingmall.domain.coupon.domain.Coupon;
import com.practice.shoppingmall.domain.coupon.domain.CouponDiscountType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
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

    @Getter
    @Builder
    private static class FindCouponResponse {
        private Long id;

        private String name;

        private CouponDiscountType discountType;

        private String discountAmount;

        private LocalDateTime expirationDate;

        public static FindCouponResponse of(Coupon coupon) {

            return FindCouponResponse
                    .builder()
                    .name(coupon.getCouponName())
                    .discountType(coupon.getDiscountType())
                    .discountAmount(coupon.getDiscountAmount())
                    .expirationDate(coupon.getExpirationDate())
                    .build();
        }
    }
}