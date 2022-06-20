package com.study.oopspring.config;

import com.study.oopspring.discount.DiscountPolicy;
import com.study.oopspring.discount.FixDiscountPolicy;
import com.study.oopspring.member.MemberRepository;
import com.study.oopspring.member.MemberService;
import com.study.oopspring.member.MemberServiceImpl;
import com.study.oopspring.member.MemoryMemberRepository;
import com.study.oopspring.order.OrderService;
import com.study.oopspring.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    // 여기만 바꾸면 됨 -----------------------------
    @Bean
    public MemberRepository memberRepository() {
        //repository에 @repository를 달면 알아서 인식해서 빈으로 등록시켜줌
        System.out.println("AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        System.out.println("AppConfig.discountPolicy");
        return new FixDiscountPolicy();
    }
    // ------------------------------------------

    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl( discountPolicy(), memberRepository());
    }

}