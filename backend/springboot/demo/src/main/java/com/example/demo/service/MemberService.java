package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        /*서비스에서 repository를 new로 인스턴스해놓고 test에서 또 new로 새로 만들어주면
        서비스에서 쓰는거랑 테스트에서 쓰는거랑 다른 repository가 되어서
        repository의 map이 static이 아닌 경우에 문제가 생길 수 있음.
        그래서 repository를 밖에서 넣어준걸 쓸 수 있게 constructor parameter를 만들어주면 됨.
        (이라고 강의에서 둘었음)
         */
    }

    //가입
    public Long join(Member member) throws SQLException { //서비스에서는 리포지토리보다 비즈니스에 더 가깝게 이름 지어줌
        validateDupicateMember(member);//중복회원검사
        memberRepository.save(member);
        return member.getId();
    }
    private void validateDupicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
        /*Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        (result.orElseGet(); = null 아니면 get, Optional로 감싸지 않을땐 이걸 쓰기도 함 )
        위와 동일한 역할을 하는 코드*/
    }

    //전체회원조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}



