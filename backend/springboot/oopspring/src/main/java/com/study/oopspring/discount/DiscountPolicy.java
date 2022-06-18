package com.study.oopspring.discount;

import com.study.oopspring.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}