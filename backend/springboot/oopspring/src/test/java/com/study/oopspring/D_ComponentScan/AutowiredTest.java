package com.study.oopspring.D_ComponentScan;

import com.study.oopspring.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {
    @Test
    void autowiredOption(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean{
        @Autowired(required = false) //대상이 없으면 호출 안됨
        public void setNoBean1(Member member){
            System.out.println("member = " + member);
        }

        @Autowired //호출은 되지만 null 반환
        public void setNoBean2(@Nullable Member member){
            System.out.println("member = " + member);
        }

        @Autowired //호출은 되지만 Optional.empty 반환
        public void setNoBean3(Optional<Member> member){
            System.out.println("member = " + member);
        }
    }

}