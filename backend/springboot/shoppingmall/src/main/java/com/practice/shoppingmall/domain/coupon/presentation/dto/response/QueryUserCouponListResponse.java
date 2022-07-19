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
public class QueryUserCouponListResponse {
    private List<FindCouponResponse> CouponList;

    public static QueryUserCouponListResponse of(List<UserCoupon> userCoupons) {

        List<FindCouponResponse> findCouponResponses = userCoupons
                .stream()
                .map(FindCouponResponse::of)
                .collect(Collectors.toList());

        return QueryUserCouponListResponse
                .builder()
                .CouponList(findCouponResponses)
                .build();
    }

    @Getter
    @Builder
    private static class FindCouponResponse {
        private Long couponId;

        private String couponName;

        private CouponDiscountType discountType;

        private String discountAmount;

        private LocalDateTime expirationDateTime;

        public static FindCouponResponse of(UserCoupon userCoupon) {

            return FindCouponResponse
                    .builder()
                    .couponId(userCoupon.getId())
                    .couponName(userCoupon.getCoupon().getCouponName())
                    .discountType(userCoupon.getCoupon().getDiscountType())
                    .discountAmount(userCoupon.getCoupon().getDiscountAmount() + userCoupon.getCoupon().getUnit())
                    .expirationDateTime(userCoupon.getExpirationDateTime())
                    .build();
        }
    }
}