package jpabook.jpashop.domain.order;

import jpabook.jpashop.domain.item.Item;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; //주문 가격
    private  int count; //주문 수량

    //생성 메서드
    public static OrderItem createOrderItem(Item item, int orderPrice, int count){
        item.removeStock(count);
        return OrderItem.builder()
                .item(item)
                .orderPrice(orderPrice)
                .count(count)
                .build();
    }

    //비즈니스 로직
    public void cancel() { getItem().addStock(count); }

    //조회 로직
    public int getTotalPrice() { return getOrderPrice() * getCount(); }
}