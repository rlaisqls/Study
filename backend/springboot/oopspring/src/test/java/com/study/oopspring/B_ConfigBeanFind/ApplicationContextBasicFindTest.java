package com.study.oopspring.B_ConfigBeanFind;

import com.study.oopspring.config.AppConfig;
import com.study.oopspring.member.MemberService;
import com.study.oopspring.member.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    //구현체가 대상이 됨
    @Test
    @DisplayName("이름으로 빈 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService",MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("타입으로 빈 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체타입으로 빈 조회")
    void findBeanByConcreteType() {
        MemberService memberService = ac.getBean(MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("find bean by name X")
    void findBeanByNameX(){
        assertThrows(NoSuchBeanDefinitionException.class,
                ()-> ac.getBean("xxxxx",MemberService.class));
    }

}