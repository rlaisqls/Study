package com.study.oopspring.order;

import com.study.oopspring.annotation.MainDiscountPolicy;
import com.study.oopspring.discount.DiscountPolicy;
import com.study.oopspring.member.Member;
import com.study.oopspring.member.MemberRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    //private final DiscountPolicy discountPolicy = new DiscountPolicy();
    //위와 같이 하면 DiscountPolicy의 구현체로 바꿔낄 수 있지만, 그 구현체에 같이 의존하게 되으모 DIP 위반
    //구현체를 변경할때 SeiveceImpl의 코드도 바꿔야 하므로 OCP 위반
    private final DiscountPolicy discountPolicy;

    private final MemberRepository memberRepository;

    public OrderServiceImpl(@MainDiscountPolicy DiscountPolicy discountPolicy, MemberRepository memberRepository) {
        this.discountPolicy = discountPolicy;
        this.memberRepository = memberRepository;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.finById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}