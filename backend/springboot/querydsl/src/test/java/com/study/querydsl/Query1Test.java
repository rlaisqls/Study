package com.study.querydsl;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.querydsl.domain.Member;
import com.study.querydsl.domain.QMember;
import com.study.querydsl.domain.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.querydsl.core.types.dsl.Expressions.constant;
import static com.study.querydsl.domain.QMember.member;
import static com.study.querydsl.domain.QTeam.team;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class Query1Test {

    @PersistenceUnit
    EntityManagerFactory emf;

    @PersistenceContext
    EntityManager em;

    JPAQueryFactory queryFactory;

    @BeforeEach
    void testEntity() {

        queryFactory = new JPAQueryFactory(em);

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
        em.flush();
    }

    @Test
    public void searchWithJPQL() {
        //given
        Member member = em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", "member1")
                .getSingleResult();
        //when
        //then
        assertEquals("member1", member.getUsername());
    }

    @Test
    public void searchWithQuerydsl() {
        //given
        Member findMember = queryFactory
                .selectFrom(member)
                .where(member.username.eq("member1"))
                .fetchOne();
        //when
        //then
        assertEquals("member1", findMember.getUsername());
    }

    @Test
    public void sort() {
        //given
        em.persist(new Member(null, 100));
        em.persist(new Member("member5", 100));
        em.persist(new Member("member6", 100));
        //when
        List<Member> results = queryFactory
                .selectFrom(member)
                .where(member.age.goe(100))
                .orderBy(member.age.desc(), member.username.asc().nullsLast())
                .fetch();
        Member member5 = results.get(0);
        Member member6 = results.get(1);
        Member memberNull = results.get(2);
        //then
        assertEquals("member5", member5.getUsername());
        assertEquals("member6", member6.getUsername());
        assertNull(memberNull.getUsername());
    }

    @Test
    void paging() {
        //given
        //when
        List<Member> results = queryFactory
                .selectFrom(member)
                .offset(0)
                .limit(2)
                .fetch();

        Long count = queryFactory
                .select(member.count())
                .from(member)
                .leftJoin(member.team, team)
                .fetchOne();
        //then
        assertEquals(2, results.size());
        assertEquals(4, count);
    }

    @Test
    void aggregation() {
        //given
        //when
        List<Tuple> results = queryFactory
                .select(
                        member.count(),
                        member.age.sum(),
                        member.age.avg(),
                        member.age.max(),
                        member.age.min()
                )
                .from(member)
                .fetch();
        //then
        Tuple tuple = results.get(0);
        assertEquals(4, tuple.get(member.count()));
        assertEquals(100, tuple.get(member.age.sum()));
        assertEquals(25, tuple.get(member.age.avg()));
        assertEquals(40, tuple.get(member.age.max()));
        assertEquals(10, tuple.get(member.age.min()));
    }

    @Test
    public void group() {
        //given
        //when
        List<Tuple> results = queryFactory
                .select(team.name, member.age.avg())
                .from(member)
                .join(member.team, team)
                .groupBy(team.name)
                .fetch();

        Tuple resultA = results.get(0);
        Tuple resultB = results.get(1);
        //then
        assertEquals("TeamA", resultA.get(team.name));
        assertEquals(15, resultA.get(member.age.avg()));
        assertEquals("TeamB", resultB.get(team.name));
        assertEquals(35, resultB.get(member.age.avg()));
    }

    @Test
    public void having() {
        //given
        //when
        List<Tuple> results = queryFactory
                .select(team.name, member.age.avg())
                .from(member)
                .join(member.team, team)
                .groupBy(team.name)
                .having(member.age.avg().gt(20))
                .fetch();

        Tuple teamB = results.get(0);
        //then
        assertEquals("TeamB", teamB.get(team.name));
        assertEquals(35, teamB.get(member.age.avg()));
    }

    @Test
    public void join() {
        //given
        //when
        List<Member> results = queryFactory
                .selectFrom(member)
                .join(member.team, team)
                .where(team.name.eq("TeamA"))
                .fetch();
        //then
        assertThat(results)
                .extracting("username")
                .containsExactly("member1", "member2");
    }

    @Test
    public void thetaJoin() {
        //given
        em.persist(new Member("m1", 20));
        em.persist(new Member("m2", 20));
        //when
        List<Member> results = queryFactory
                .select(member)
                .from(member, team)
                .where(member.username.length().lt(team.name.length()))
                .fetch();
        //then
        Member memberA = results.get(0);
        Member memberB = results.get(1);

        assertEquals("m1", memberA.getUsername());
        assertEquals("m1", memberB.getUsername());
    }

    @Test
    void join_On() {
        //given
        //when
        List<Tuple> results = queryFactory
                .select(member, team)
                .from(member)
                .leftJoin(member.team, team)
                .on(team.name.eq("TeamA"))
                .fetch();

        List<String> teamAList = results
                .stream()
                .filter(tuple -> tuple.get(team) != null && Objects.equals(tuple.get(team).getName(), "TeamA"))
                .map(tuple -> tuple.get(member).getUsername())
                .collect(Collectors.toList());

        List<String> teamBList = results
                .stream()
                .filter(tuple -> tuple.get(team) == null)
                .map(tuple -> tuple.get(member).getUsername())
                .collect(Collectors.toList());
        //then
        assertThat(teamAList).contains("member1", "member2");
        assertThat(teamBList).contains("member3", "member4");
    }

    @Test
    public void fetchJoin() {
        //given
        em.flush();
        em.clear();
        //when
        Member findMember = queryFactory
                .selectFrom(member)
                .join(member.team, team)
                .fetchJoin()
                .where(member.username.eq("member1"))
                .fetchOne();
        //then
        boolean isLoaded = emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam());
        assertTrue(isLoaded);
    }

    @Test
    public void subQuery() {
        //given
        QMember qMember = new QMember("m");
        //when
        Member findMember = queryFactory
                .selectFrom(member)
                .where(member.age.eq(
                        JPAExpressions
                                .select(qMember.age.max())
                                .from(qMember)
                ))
                .fetchOne();
        //then
        assertEquals(40, findMember.getAge());
    }

    @Test
    public void subQueryIn() {
        //given
        QMember qMember = new QMember("m");
        //when
        List<Member> findMembers = queryFactory
                .selectFrom(member)
                .where(member.age.in(
                        JPAExpressions
                                .select(qMember.age)
                                .from(qMember)
                                .where(qMember.age.in(10))
                ))
                .fetch();
        //then
        assertEquals(1, findMembers.size());
        assertEquals(10, findMembers.get(0).getAge());
    }

    @Test
    public void basicCase() {
        //given
        //when
        List<String> results = queryFactory
                .select(member.age
                        .when(10).then("열살")
                        .when(20).then("스무살")
                        .otherwise("기타"))
                .from(member)
                .fetch();
        //then
        results.forEach(System.out::println);
    }

    @Test
    public void complexCase() {
        //given
        //when
        List<String> results = queryFactory
                .select(new CaseBuilder()
                        .when(member.age.between(0, 20)).then("0-20세")
                        .when(member.age.between(21, 30)).then("21-30세")
                        .otherwise("기타"))
                .from(member)
                .fetch();
        //then
        results.forEach(System.out::println);
    }

    @Test
    public void orderByCase() {
        //given
        NumberExpression<Integer> rankCase = new CaseBuilder()
                .when(member.age.between(0, 20)).then(2)
                .when(member.age.between(21, 30)).then(1)
                .otherwise(3);
        //when
        List<Tuple> results = queryFactory
                .select(member.username, member.age, rankCase)
                .from(member)
                .orderBy(rankCase.asc())
                .fetch();
        //then
        results.forEach(System.out::println);
    }

    @Test
    public void addConstant() {
        //given
        //when
        List<Tuple> results = queryFactory
                .select(member.username, constant("A"))
                .from(member)
                .fetch();
        //then
        results.forEach(System.out::println);
    }

    @Test
    public void concat() {
        //given
        //when
        String result = queryFactory
                .select(member.username.concat("_").concat(member.age.stringValue()))
                .from(member)
                .where(member.username.eq("member1"))
                .fetchOne();
        //then
        System.out.println(result);
    }
}