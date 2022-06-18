package com.study.oopspring.member;


import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    //HashMap에서 동시성 문제를 해결한 ConcurrentHashMap라는 게 있기는 함
    //근데 이건 그냥 연습용이기 때문에... 다음에 기회가 있으면 알아보도록 하자

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member finById(Long memberId) {
        return store.get(memberId);
    }
}