package com.practice.shoppingmall.domain.coupon.domain;

import com.practice.shoppingmall.domain.coupon.exception.DiscountOutOfRangeException;
import com.practice.shoppingmall.domain.coupon.exception.InvalidCouponException;
import com.practice.shoppingmall.domain.coupon.presentation.dto.request.CreateCouponRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon {

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private final List<UserCoupon> users = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long id;

    private String couponName;

    private CouponDiscountType discountType;

    private Integer discountAmount;

    private Long validityPeriod;

    public static Coupon of(CreateCouponRequest request) {

        if (request.getDiscountType() == CouponDiscountType.RATE && request.getDiscountAmount() >= 100) {
            throw DiscountOutOfRangeException.EXCEPTION;
        }

        return Coupon.builder()
                .couponName(request.getName())
                .discountType(request.getDiscountType())
                .discountAmount(request.getDiscountAmount())
                .validityPeriod(request.getValidityPeriod())
                .build();
    }


    public int doDiscount(int totalPrice) {

        switch (this.discountType) {
            case RATE:
                return totalPrice - (totalPrice * this.discountAmount / 100);
            case FIXED:
                if (totalPrice < this.discountAmount) return 0;
                else return totalPrice - this.discountAmount;
            default:
                throw InvalidCouponException.EXCEPTION;
        }
    }

    public String getUnit() {
        switch (this.discountType) {
            case RATE:
                return "%";
            case FIXED:
                return "원";
            default:
                throw InvalidCouponException.EXCEPTION;
        }
    }
}