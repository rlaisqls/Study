package com.practice.shoppingmall.domain.order.service;

import com.practice.shoppingmall.domain.order.domain.Order;
import com.practice.shoppingmall.domain.order.domain.repository.OrderRepository;
import com.practice.shoppingmall.domain.order.presentation.dto.response.QueryOrderListResponse;
import com.practice.shoppingmall.domain.user.domain.User;
import com.practice.shoppingmall.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryOrderListService {

    private final UserFacade userFacade;

    private final OrderRepository orderRepository;

    public QueryOrderListResponse execute() {

        User user = userFacade.getCurrentUser();

        List<Order> orderList = orderRepository.findByUser(user);

        return QueryOrderListResponse.of(orderList);
    }
}