package com.study.oopspring.C_SpringSingleton;

import com.study.oopspring.AppConfig;
import com.study.oopspring.member.MemberRepository;
import com.study.oopspring.member.MemberServiceImpl;
import com.study.oopspring.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ConfigurationSingletonTest {
    @Test
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository",MemberRepository.class);

        assertThat(memberRepository).isEqualTo(memberService.getMemberRepository());
        assertThat(memberRepository).isEqualTo(orderService.getMemberRepository());
    }

    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);
        System.out.println("bean.getClass() = " + bean.getClass());
        /*class com.study.oopspring.AppConfig$$EnhancerBySpringCGLIB$$c5d3ba9
        스프링이 CGLIB라는 바이트 코드 조작 라이브러리를 사용해서 AppConfig를 상속받은
        임의의 다른 클래스를 만들어서 빈으로 등록했기 때문에 위처럼 뜬다.*/
    }
}