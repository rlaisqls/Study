package com.study.querydsl.repository;

import com.study.querydsl.domain.Member;
import com.study.querydsl.domain.Team;
import com.study.querydsl.dto.MemberSearchCondition;
import com.study.querydsl.dto.MemberTeamDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@Transactional
public class MemberJpaRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    public void basicTest() {
        //given
        Member member = new Member("김은빈", 17);
        memberJpaRepository.save(member);
        //when
        Member findMember = memberJpaRepository.findById(member.getId()).get();
        List<Member> result1 = memberJpaRepository.findAll();
        List<Member> result2 = memberJpaRepository.findByUsername("김은빈");
        //then
        assertEquals(member, findMember);
        assertTrue(result1.contains(member));
        assertTrue(result2.contains(member));
    }

    @Test
    public void basicQuerydslTest() {
        //given
        Member member = new Member("김은빈", 17);
        memberJpaRepository.save(member);
        //when
        List<Member> result1 = memberJpaRepository.findAll_Querydsl();
        List<Member> result2 = memberJpaRepository.findByUsername_Querydsl("김은빈");
        //then
        assertTrue(result1.contains(member));
        assertTrue(result2.contains(member));
    }

    @Test
    public void searchTest_Builder() {
        //given
        Team teamA = new Team("TeamA");
        Team teamB = new Team("TeamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        MemberSearchCondition condition = MemberSearchCondition
                .builder()
                .ageGoe(35)
                .ageLoe(40)
                .teamName("TeamB")
                .build();

        //when
        List<MemberTeamDto> result = memberJpaRepository.searchByBuilder(condition);
        //then
        assertEquals(result.get(0).getUsername(), "member4");
    }

    @Test
    public void searchTest_Where() {
        //given
        Team teamA = new Team("TeamA");
        Team teamB = new Team("TeamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        MemberSearchCondition condition = MemberSearchCondition
                .builder()
                .ageGoe(35)
                .ageLoe(40)
                .teamName("TeamB")
                .build();

        //when
        List<MemberTeamDto> result = memberJpaRepository.searchByWhere(condition);
        //then
        assertEquals(result.get(0).getUsername(), "member4");
    }
}