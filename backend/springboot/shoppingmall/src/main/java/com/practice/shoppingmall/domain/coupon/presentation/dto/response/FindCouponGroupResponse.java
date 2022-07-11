package com.practice.shoppingmall.domain.coupon.presentation.dto.response;

import com.practice.shoppingmall.domain.coupon.domain.CouponDiscountType;
import com.practice.shoppingmall.domain.coupon.domain.UserCoupon;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Builder
public class FindCouponGroupResponse {
    private List<FindCouponResponse> CouponList;

    public static FindCouponGroupResponse of(List<UserCoupon> userCoupons) {

        List<FindCouponResponse> findCouponResponses = userCoupons
                .stream()
                .map(FindCouponResponse::of)
                .collect(Collectors.toList());

        return FindCouponGroupResponse
                .builder()
                .CouponList(findCouponResponses)
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

        public static FindCouponResponse of(UserCoupon userCoupon) {

            return FindCouponResponse
                    .builder()
                    .id(userCoupon.getId())
                    .name(userCoupon.getCoupon().getCouponName())
                    .discountType(userCoupon.getCoupon().getDiscountType())
                    .discountAmount(userCoupon.getCoupon().getDiscountAmount())
                    .expirationDate(userCoupon.getExpirationDate())
                    .build();
        }
    }
}