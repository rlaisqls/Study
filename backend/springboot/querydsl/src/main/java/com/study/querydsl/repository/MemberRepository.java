package com.study.querydsl.repository;

import com.study.querydsl.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MemberRepository extends JpaRepository<Member, Long>, CustomMemberRepository, QuerydslPredicateExecutor<Member> {
}