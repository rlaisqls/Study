package com.practice.shoppingmall.domain.order.service;

import com.practice.shoppingmall.domain.order.domain.Order;
import com.practice.shoppingmall.domain.order.domain.repository.OrderRepository;
import com.practice.shoppingmall.domain.order.exception.OrderNotFoundException;
import com.practice.shoppingmall.domain.order.presentation.dto.response.QueryOrderInfoResponse;
import com.practice.shoppingmall.domain.user.domain.User;
import com.practice.shoppingmall.domain.user.exception.ForbiddenUserException;
import com.practice.shoppingmall.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class QueryOrderInfoService {

    private final UserFacade userFacade;

    private final OrderRepository orderRepository;

    public QueryOrderInfoResponse execute(Long id) {

        User user = userFacade.getCurrentUser();

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> OrderNotFoundException.EXCEPTION);

        if (order.getUser() != user) throw ForbiddenUserException.EXCEPTION;

        return QueryOrderInfoResponse.of(order);
    }
}