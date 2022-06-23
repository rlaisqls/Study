package com.practice.shoppingmall.controller;

import com.practice.shoppingmall.dto.request.item.OrderItemRequest;
import com.practice.shoppingmall.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order")
    public void order(List<OrderItemRequest> requests){
        orderService.order(requests);
    }

    @DeleteMapping("/order/{orderUuid}")
    public void cancelOrder(@PathVariable String orderUuid){

    }
}