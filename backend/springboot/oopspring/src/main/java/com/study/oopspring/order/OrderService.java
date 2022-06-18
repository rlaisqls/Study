package com.study.oopspring.order;

public interface OrderService {
    //주문 결과 생성 후 그냥 반환
    Order createOrder(Long memberId, String itemName, int itemPrice);
}