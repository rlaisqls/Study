package com.practice.shoppingmall.domain.coupon.domain.repository;

import com.practice.shoppingmall.domain.coupon.domain.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCouponRepository extends JpaRepository<UserCoupon, Long> {
}