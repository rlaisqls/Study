package com.study.oopspring.member;

public class MemberServiceImpl implements MemberService{

    //추상화에만 의존할 수 있도록 DI
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.finById(memberId);
    }
}