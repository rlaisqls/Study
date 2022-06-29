package com.practice.shoppingmall.entity.order;

import com.practice.shoppingmall.entity.delivery.Delivery;
import com.practice.shoppingmall.entity.delivery.DeliveryStatus;
import com.practice.shoppingmall.entity.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    @Id
    @Column(name = "order_id", columnDefinition = "BINARY(16)")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /*한 주문이 여러개의 아이템을 가질 수 있고, 한 아이템이 여러개의 주문에 호출될 수 있으므로
    다대다 매핑을 해줘야하는데 RDB는 다대다 관계를 표현할 수 없기 때문에 자동으로 중간 테이블을 만들어 구현한다.
    하지만 그런 방식을 쓰면 예상치 못한 쿼리가 나가거나 오류가 생기기 쉬우므로 중간에 orderItem 테이블을 만들어서 직접 일대다, 다대일 관계로 풀어서 사용한다.*/
    @Setter
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) //cascade.All이기 때문에 orders를 persist해주면 같이 저장된다.
    private List<OrderItem> orderItems;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
    }


    public void cancel() {
        if(delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }

        this.status = OrderStatus.CANCEL;
        for (OrderItem orderItem :this.orderItems) {
            orderItem.cancel();
        }
    }
}