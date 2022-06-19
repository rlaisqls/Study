package com.study.oopspring.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    //추상화에만 의존할 수 있도록 DI
    private final MemberRepository memberRepository;

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.finById(memberId);
    }

    //테스트용
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}