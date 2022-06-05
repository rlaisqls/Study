package jpabook.jpashop.repository.queryDsl;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jpabook.jpashop.domain.order.Order;
import jpabook.jpashop.domain.order.OrderSearch;
import jpabook.jpashop.domain.order.OrderStatus;
import jpabook.jpashop.domain.order.QOrder;
import jpabook.jpashop.domain.member.QMember;

import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

import static jpabook.jpashop.domain.member.QMember.member;
import static jpabook.jpashop.domain.order.QOrder.order;

@RequiredArgsConstructor
public class CustomizedOrderRepositoryImpl implements CustomizedOrderRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Order> findOrders(OrderSearch orderSearch){
        QOrder order = QOrder.order;
        QMember member = QMember.member;
        return jpaQueryFactory
                .select(order)
                .from(order)
                .join(order.member, member)
                .where(statusEq(orderSearch.getOrderStatus()),
                        nameLike(orderSearch.getMemberName()))
                .limit(1000)
                .fetch();
    }
    private BooleanExpression statusEq(OrderStatus statusCond){
        if(statusCond == null) return null;
        else return order.status.eq(statusCond);
    }
    private BooleanExpression nameLike(String nameCond){
        if(!StringUtils.hasText(nameCond)) return null;
        else return member.name.like(nameCond);
    }
}