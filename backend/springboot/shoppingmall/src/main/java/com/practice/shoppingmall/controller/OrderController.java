package com.practice.shoppingmall.controller;

import com.practice.shoppingmall.dto.request.order.OrderRequest;
import com.practice.shoppingmall.dto.response.ResponseBody;
import com.practice.shoppingmall.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public void order(@Valid @RequestBody OrderRequest requests){
        orderService.order(requests);
    }

    @PatchMapping("/order/{uuid}")
    public void cancelOrder(@PathVariable String uuid){
        orderService.cancelOrder(uuid);
    }

    @GetMapping("/order/{uuid}")
    public ResponseBody findOrder(@PathVariable String uuid){
        return ResponseBody.of(orderService.findOrder(uuid), HttpStatus.OK.value());
    }

    @GetMapping("/order")
    public ResponseBody findMyOrder(int page, int size){
        return ResponseBody.of(orderService.findMyOrder(page, size), HttpStatus.OK.value());
    }
}