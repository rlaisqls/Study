package com.example.demo.service;
import com.example.demo.SpringConfig;
import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = { SpringConfig.class}, loader = AnnotationConfigContextLoader.class)
@PersistenceContext
@Transactional
@TestExecutionListeners({})
class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Test
    void signup() throws SQLException {
        //given
        Member member = new Member();
        member.setName("helpme");
        member.setId(123L);
        System.out.println(member.getName()+" "+member.getId()+"^~^");
        //when
        Long saveId = 123L;

        memberRepository.save(member);
        //memberService.join(member);
        //then
        Member findMember = memberService.findOne(123L).get();
        assertThat(findMember.getName()).isEqualTo(findMember.getName()); //alt enter -> static import
    }

    /*
    @Test
    void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring"); //shift f6 -> rename

        //when
        memberService.join(member1);

        //then
        IlligalStateException e = assertThrows(IlligalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //try {
        //    memberService.join(member2);
        //    fail();
        //} catch (IlligalStateException e) {
        //    assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.")
        //}
    }*/
}
