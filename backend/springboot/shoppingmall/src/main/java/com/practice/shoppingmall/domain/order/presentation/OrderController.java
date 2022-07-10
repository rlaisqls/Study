package com.practice.shoppingmall.domain.order.presentation;

import com.practice.shoppingmall.domain.order.presentation.dto.request.OrderRequest;
import com.practice.shoppingmall.domain.order.presentation.dto.response.CreateOrderResponse;
import com.practice.shoppingmall.domain.order.presentation.dto.response.FindOrderGroupResponse;
import com.practice.shoppingmall.domain.order.presentation.dto.response.FindOrderInfoResponse;
import com.practice.shoppingmall.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order")
    public CreateOrderResponse doOrder(@Valid @RequestBody OrderRequest requests){
        return orderService.doOrder(requests);
    }

    @PatchMapping("/order/{orderId}")
    public void cancelOrder(@PathVariable Long orderId){
        orderService.cancelOrder(orderId);
    }

    @GetMapping("/order/{orderId}")
    public FindOrderInfoResponse findOneOrder(@PathVariable Long orderId){
        return orderService.findOneOrder(orderId);
    }

    @GetMapping("/order")
    public FindOrderGroupResponse findMyOrder(int page, int size){
        return orderService.findMyOrder(page, size);
    }
}