package com.study.oopspring.member;

public interface MemberRepository {
    void save(Member member);
    Member finById(Long memberId);
}