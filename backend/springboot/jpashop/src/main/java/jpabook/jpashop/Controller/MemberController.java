package jpabook.jpashop.Controller;

import jpabook.jpashop.entity.member.Member;
import jpabook.jpashop.payload.request.CreateMemberRequest;
import jpabook.jpashop.payload.request.UpdateMemberRequest;
import jpabook.jpashop.payload.response.CreateMemberResponse;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import javax.validation.Valid;
import java.util.List;

/* entity는 많은 곳에서 사용되기 떄문에 직접 수정되는건 위험하다.
하지만 제약조건이나 받는 정보의 종류같은 세부 사항이 바뀌어야 할 때가 있다.
그럴때마다 entity를 수정하면 코드 유지보수가 힘들어진다.
그렇기 때문에 dto(Data Transfer Object)를 사용해서 입력, 반환해야한다.*/

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/member")
    public CreateMemberResponse joinMember(@RequestBody @Valid CreateMemberRequest request){
        return memberService.join(request); //id 리턴
    }

    @PatchMapping("/member/{id}")
    public void updateMember(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequest request){
        memberService.update(id,request);
    }

    @GetMapping("/member")
    public Result<List<MemberDto>> findAllMember(){
        return new Result<>(memberService.findMember().stream()
                .map(m -> new MemberDto(m.getName()))
                .collect(Collectors.toList()));
    }

    @GetMapping("/member/{id}")
    public MemberDto findMember(@PathVariable("id") Long id){
        return new MemberDto(memberService.findMemberById(id));
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String name;
        public MemberDto(Member member) {
            name = member.getName();
        }
    }

}