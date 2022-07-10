package com.practice.shoppingmall.domain.order.service;

import com.practice.shoppingmall.domain.coupon.domain.UserCoupon;
import com.practice.shoppingmall.domain.coupon.domain.repository.UserCouponRepository;
import com.practice.shoppingmall.domain.delivery.domain.Delivery;
import com.practice.shoppingmall.domain.delivery.domain.DeliveryStatus;
import com.practice.shoppingmall.domain.item.domain.Item;
import com.practice.shoppingmall.domain.item.domain.repository.ItemRepository;
import com.practice.shoppingmall.domain.item.exception.ItemNotFoundException;
import com.practice.shoppingmall.domain.order.domain.Order;
import com.practice.shoppingmall.domain.order.domain.OrderItem;
import com.practice.shoppingmall.domain.order.domain.OrderStatus;
import com.practice.shoppingmall.domain.order.domain.repository.OrderRepository;
import com.practice.shoppingmall.domain.coupon.exception.CouponNotFoundException;
import com.practice.shoppingmall.domain.order.exception.OrderNotFoundException;
import com.practice.shoppingmall.domain.order.presentation.dto.request.OrderItemRequest;
import com.practice.shoppingmall.domain.order.presentation.dto.request.OrderRequest;
import com.practice.shoppingmall.domain.order.presentation.dto.response.CreateOrderResponse;
import com.practice.shoppingmall.domain.order.presentation.dto.response.FindOrderGroupResponse;
import com.practice.shoppingmall.domain.order.presentation.dto.response.FindOrderInfoResponse;
import com.practice.shoppingmall.domain.user.domain.User;
import com.practice.shoppingmall.domain.user.exception.ForbiddenUserException;
import com.practice.shoppingmall.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserFacade userFacade;

    private final ItemRepository itemRepository;

    private final OrderRepository orderRepository;

    private final UserCouponRepository userCouponRepository;

    @Override
    @Transactional
    public CreateOrderResponse doOrder(OrderRequest request) {

        User user = userFacade.nowUser();

        Order order = Order.builder()
                .user(user)
                .delivery(deliveryStart(user))
                .orderStatus(OrderStatus.ORDER)
                .orderDate(LocalDateTime.now())
                .build();

        List<OrderItem> orderItems = request.getOrderItems()
                .stream()
                .map(orderItem -> getOrderItemOfOrder(orderItem, order))
                .collect(Collectors.toList());

        order.setOrderItems(orderItems);

        orderRepository.save(order);

        return new CreateOrderResponse(order.getId().toString());
    }

    private Delivery deliveryStart(User user) {

        return Delivery
                .builder()
                .address(user.getAddress())
                .deliveryStatus(DeliveryStatus.READY)
                .build();
    }

    private OrderItem getOrderItemOfOrder(OrderItemRequest request, Order order) {

        Item item = itemRepository.findById(request.getItemId())
                .orElseThrow(() -> ItemNotFoundException.EXCEPTION);

        item.removeStock(request.getCount());

        OrderItem orderItem = OrderItem.builder()
                .order(order)
                .item(item)
                .count(request.getCount())
                .orderPrice(item.getPrice())
                .totalPrice(item.getPrice() * request.getCount())
                .build();

        if (request.getItemId() != null) {
            UserCoupon userCoupon = validateAndGetCoupon(request.getCouponId());
            orderItem.applyCoupon(userCoupon.getCoupon());
            deleteUserCoupon(userCoupon);
        }

        return orderItem;
    }

    private UserCoupon validateAndGetCoupon(Long couponId) {

        User user = userFacade.nowUser();
        List<UserCoupon> userCoupons = userCouponRepository.findByUserAndCoupon_Id(user, couponId);
        if (userCoupons.isEmpty()) throw CouponNotFoundException.EXCEPTION;

        return userCoupons.get(0);
    }

    private void deleteUserCoupon(UserCoupon userCoupon) {
        userCouponRepository.delete(userCoupon);
    }

    @Override
    public FindOrderInfoResponse findOneOrder(Long id) {

        User user = userFacade.nowUser();

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> OrderNotFoundException.EXCEPTION);

        if (!order.getUser().equals(user)) throw ForbiddenUserException.EXCEPTION;

        return FindOrderInfoResponse.of(order);
    }

    @Override
    public FindOrderGroupResponse findMyOrder(int page, int size) {

        User user = userFacade.nowUser();

        Pageable request = PageRequest.of(page, size);
        Page<Order> orderPage = orderRepository.findByUser(user, request);

        return FindOrderGroupResponse.of(orderPage);
    }

    @Override
    @Transactional
    public void cancelOrder(Long id) {

        User user = userFacade.nowUser();

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> OrderNotFoundException.EXCEPTION);

        if (!order.getUser().equals(user)) throw ForbiddenUserException.EXCEPTION;

        order.cancel();
        orderRepository.save(order);
    }

}