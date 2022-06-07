package jpabook.jpashop.entity.delivery;

import jpabook.jpashop.entity.address.Address;
import jpabook.jpashop.entity.order.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded //복합 값 타입이라고도 함, 비슷한 값끼리 묶어주는 느낌
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; //READY(준비), COMP(배송)
}