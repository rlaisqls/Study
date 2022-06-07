package jpabook.jpashop.entity.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;
}