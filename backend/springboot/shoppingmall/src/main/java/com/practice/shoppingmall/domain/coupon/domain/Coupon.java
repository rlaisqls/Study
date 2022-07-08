package com.practice.shoppingmall.domain.coupon.domain;

import com.practice.shoppingmall.domain.coupon.exception.DiscountOutOfRangeException;
import com.practice.shoppingmall.domain.coupon.exception.InvalidCouponException;
import com.practice.shoppingmall.domain.coupon.presentation.dto.request.CreateCouponRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long id;

    private String couponName;

    private CouponDiscountType discountType;

    @Nullable
    private int discountPrice;

    @Nullable
    private int discountRate;

    private LocalDateTime expirationDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private final List<UserCoupon> users = new ArrayList<>();

    public static Coupon of(CreateCouponRequest request) {

        if(request.getDiscountType() == CouponDiscountType.RATE && request.getDiscountAmount() >= 100) {
            throw DiscountOutOfRangeException.EXCEPTION;
        }

        return Coupon
                .builder()
                .couponName(request.getName())
                .discountType(request.getDiscountType())
                .discountRate(request.getDiscountAmount())
                .expirationDate(LocalDateTime.now().plusMinutes(request.getExpirationTime()))
                .build();
    }

    public int doDiscount(int totalPrice) {

        int discountedTotalPrice;

        switch (this.getDiscountType()){
            case RATE:
                discountedTotalPrice = totalPrice * this.discountRate / 100;
                break;
            case FIXED:
                if(totalPrice < this.discountPrice) discountedTotalPrice = 0;
                else discountedTotalPrice = totalPrice - this.discountPrice;
                break;
            default:
                throw InvalidCouponException.EXCEPTION;
        }

        return discountedTotalPrice;
    }

    public String getDiscountAmount() {

        String discountAmount;

        switch (this.getDiscountType()){
            case RATE:
                discountAmount = String.valueOf(this.discountRate) + "%";
                break;
            case FIXED:
                discountAmount = String.valueOf(this.discountPrice) + "원";
                break;
            default:
                throw InvalidCouponException.EXCEPTION;
        }

        return discountAmount;
    }
}