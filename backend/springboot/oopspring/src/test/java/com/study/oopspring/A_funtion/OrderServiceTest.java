package com.study.oopspring.A_funtion;

import com.study.oopspring.config.AppConfig;
import com.study.oopspring.member.Grade;
import com.study.oopspring.member.Member;
import com.study.oopspring.member.MemberService;
import com.study.oopspring.order.Order;
import com.study.oopspring.order.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    @DisplayName("주문 생성")
    void createOrder(){
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Long memberId = 1L;
        Member member = new Member(memberId,"memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}