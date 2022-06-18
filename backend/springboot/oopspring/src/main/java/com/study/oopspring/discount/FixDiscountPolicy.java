package com.study.oopspring.discount;

import com.study.oopspring.member.Grade;
import com.study.oopspring.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {


    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}