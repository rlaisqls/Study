package jpabook.jpashop.service;

import jpabook.jpashop.entity.member.Member;
import jpabook.jpashop.payload.request.CreateMemberRequest;
import jpabook.jpashop.payload.request.UpdateMemberRequest;
import jpabook.jpashop.payload.response.CreateMemberResponse;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    /* @RequiredArgsConstructor가 아래와 같은 생성자를 자동 생성
    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    } */
    @Transactional(readOnly = false)
    public CreateMemberResponse join(CreateMemberRequest request){
        Member member = Member.from(request);
        if(!memberRepository.findByName(member.getName()).isEmpty())
            throw new IllegalStateException("이미 존재하는 회원입니다");

        memberRepository.save(member);
        return new CreateMemberResponse(member.getId());
    }

    @Transactional
    public void update(Long id, UpdateMemberRequest request){
        Optional<Member> optionalMember = memberRepository.findById(id);
        optionalMember.ifPresent(member -> {
            member.setAddress(request.getAddress());
            member.setName(request.getName());
            memberRepository.save(member);
        });
    }

    public List<Member> findMember(){
        return memberRepository.findAll();
    }

    public Member findMemberById(Long memberId){
        return memberRepository.findById(memberId).get();
    }

}