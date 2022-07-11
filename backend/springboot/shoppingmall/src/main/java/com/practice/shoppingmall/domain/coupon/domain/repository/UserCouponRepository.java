package com.practice.shoppingmall.domain.coupon.domain.repository;

import com.practice.shoppingmall.domain.coupon.domain.UserCoupon;
import com.practice.shoppingmall.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserCouponRepository extends CrudRepository<UserCoupon, Long> {

    List<UserCoupon> findByUser(User user);
}