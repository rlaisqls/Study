package com.practice.shoppingmall.domain.coupon.domain.repository;

import com.practice.shoppingmall.domain.coupon.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CouponRepository extends JpaRepository<Coupon, UUID> {
}