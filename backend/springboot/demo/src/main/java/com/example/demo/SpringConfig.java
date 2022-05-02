package com.example.demo;


import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MemoryMemberRepository;
import com.example.demo.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    /*자바 코드로 직접 등록
    각 class에 @Service랑 @Repository 붙여주는거랑 똑같음
    정형화되지 않은 클래스나 상황에 따라 구현 클래스를 병경해야할떄 주로 씀
     */
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}

