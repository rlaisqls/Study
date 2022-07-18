package com.practice.shoppingmall.domain.order.presentation;

import com.practice.shoppingmall.domain.order.presentation.dto.request.OrderRequest;
import com.practice.shoppingmall.domain.order.presentation.dto.response.CreateOrderResponse;
import com.practice.shoppingmall.domain.order.presentation.dto.response.FindOrderInfoResponse;
import com.practice.shoppingmall.domain.order.presentation.dto.response.FindOrderListResponse;
import com.practice.shoppingmall.domain.order.service.CancelOrderService;
import com.practice.shoppingmall.domain.order.service.DoOrderService;
import com.practice.shoppingmall.domain.order.service.QueryOrderInfoService;
import com.practice.shoppingmall.domain.order.service.QueryUserOrderListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final CancelOrderService cancelOrderService;

    private final DoOrderService doOrderService;

    private final QueryOrderInfoService queryOrderInfoService;

    private final QueryUserOrderListService queryUserOrderListService;

    @PostMapping("/order")
    public CreateOrderResponse doOrder(@Valid @RequestBody OrderRequest requests){
        return doOrderService.execute(requests);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/order/{orderId}")
    public void cancelOrder(@PathVariable Long orderId){
        cancelOrderService.execute(orderId);
    }

    @GetMapping("/order/{orderId}")
    public FindOrderInfoResponse queryOrderInfo(@PathVariable Long orderId){
        return queryOrderInfoService.execute(orderId);
    }

    @GetMapping("/order")
    public FindOrderListResponse queryUserOrderList(){
        return queryUserOrderListService.execute();
    }
}