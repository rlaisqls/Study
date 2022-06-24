package com.practice.shoppingmall.controller;

import com.practice.shoppingmall.dto.request.item.OrderItemRequest;
import com.practice.shoppingmall.service.OrderService;
import com.practice.shoppingmall.service.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order")
    public void order(@Valid @RequestBody List<OrderItemRequest> requests){
        orderService.order(requests);
    }

    @DeleteMapping("/order/{orderUuid}")
    public void cancelOrder(@PathVariable String orderUuid){

    }
}