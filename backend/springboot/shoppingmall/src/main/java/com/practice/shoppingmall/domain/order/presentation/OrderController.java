package com.practice.shoppingmall.domain.order.presentation;

import com.practice.shoppingmall.domain.order.presentation.dto.request.OrderItemRequest;
import com.practice.shoppingmall.domain.order.presentation.dto.response.CreateOrderResponse;
import com.practice.shoppingmall.domain.order.presentation.dto.response.QueryOrderInfoResponse;
import com.practice.shoppingmall.domain.order.presentation.dto.response.QueryOrderListResponse;
import com.practice.shoppingmall.domain.order.service.CancelOrderService;
import com.practice.shoppingmall.domain.order.service.CreateOrderService;
import com.practice.shoppingmall.domain.order.service.QueryOrderInfoService;
import com.practice.shoppingmall.domain.order.service.QueryOrderListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final CreateOrderService createOrderService;

    private final CancelOrderService cancelOrderService;

    private final QueryOrderInfoService queryOrderInfoService;

    private final QueryOrderListService queryOrderListService;

    @PostMapping
    public CreateOrderResponse createOrder(@Valid @RequestBody List<OrderItemRequest> requests){
        return createOrderService.execute(requests);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{order-id}")
    public void cancelOrder(@PathVariable("order-id") Long orderId){
        cancelOrderService.execute(orderId);
    }

    @GetMapping("/{order-id}")
    public QueryOrderInfoResponse queryOrderInfo(@PathVariable("order-id") Long orderId){
        return queryOrderInfoService.execute(orderId);
    }

    @GetMapping
    public QueryOrderListResponse queryOrderList(){
        return queryOrderListService.execute();
    }
}