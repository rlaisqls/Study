package jpabook.jpashop.entity.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jpabook.jpashop.entity.address.Address;
import jpabook.jpashop.entity.order.Order;
import jpabook.jpashop.payload.request.CreateMemberRequest;
import jpabook.jpashop.payload.request.UpdateMemberRequest;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    //entity에는 프리젠테이션 계층을 위한 설정을 넣지 않아야함 (API 스펙이 변하지 않도록)
    //그게 필요할때는 dto로 만들어서 따로 관리해줌
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String password;

    @Embedded
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    public static Member from(CreateMemberRequest memberRequest){
        return Member.builder()
                .name(memberRequest.getName())
                .address(memberRequest.getAddress())
                .build();
    }

    public static Member from(UpdateMemberRequest memberRequest){
        return Member.builder()
                .name(memberRequest.getName())
                .address(memberRequest.getAddress())
                .build();
    }
}