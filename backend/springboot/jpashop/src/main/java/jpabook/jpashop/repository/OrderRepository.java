package jpabook.jpashop.repository;

import jpabook.jpashop.domain.order.Order;
import jpabook.jpashop.repository.queryDsl.CustomizedOrderRepository;
import jpabook.jpashop.repository.queryDsl.CustomizedOrderRepositoryImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>, CustomizedOrderRepository {

}