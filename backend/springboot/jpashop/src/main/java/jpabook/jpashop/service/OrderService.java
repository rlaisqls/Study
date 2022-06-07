package jpabook.jpashop.service;

import jpabook.jpashop.entity.delivery.Delivery;
import jpabook.jpashop.entity.member.Member;
import jpabook.jpashop.entity.order.Order;
import jpabook.jpashop.entity.order.OrderItem;
import jpabook.jpashop.entity.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

//도메인 모델 패턴
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public long order(Long memberId, Long itemId, int count){
        Optional<Member> member = memberRepository.findById(memberId);
        Optional<Item> item = itemRepository.findById(itemId);

        if(!member.isPresent()||!item.isPresent()) throw new IllegalStateException();

        Delivery delivery = new Delivery();
        delivery.setAddress(member.get().getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(item.get(), item.get().getPrice(), count);
        Order order = orderRepository.save(Order.createOrder(member.get(), delivery, orderItem));
        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(IllegalAccessError::new);
        order.cancel();
    }

}