package com.practice.shoppingmall.domain.coupon.domain.repository;

import com.practice.shoppingmall.domain.coupon.domain.Coupon;
import org.springframework.data.repository.CrudRepository;


public interface CouponRepository extends CrudRepository<Coupon, Long> {
}