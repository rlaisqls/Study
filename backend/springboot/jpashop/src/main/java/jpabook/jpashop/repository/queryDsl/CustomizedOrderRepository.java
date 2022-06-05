package jpabook.jpashop.repository.queryDsl;

import jpabook.jpashop.domain.order.Order;
import jpabook.jpashop.domain.order.OrderSearch;

import java.util.List;

public interface CustomizedOrderRepository {
    List<Order> findOrders(OrderSearch orderSearch);
}