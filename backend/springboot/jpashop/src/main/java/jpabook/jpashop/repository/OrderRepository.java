package jpabook.jpashop.repository;

import jpabook.jpashop.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByMember_IdLike(Long member_id);
}