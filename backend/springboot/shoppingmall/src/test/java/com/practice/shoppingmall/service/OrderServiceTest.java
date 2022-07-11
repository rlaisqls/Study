package com.practice.shoppingmall.service;

import com.practice.shoppingmall.constant.CouponConstant;
import com.practice.shoppingmall.constant.ItemConstant;
import com.practice.shoppingmall.constant.OrderConstant;
import com.practice.shoppingmall.domain.coupon.domain.Coupon;
import com.practice.shoppingmall.domain.coupon.domain.UserCoupon;
import com.practice.shoppingmall.domain.coupon.domain.repository.UserCouponRepository;
import com.practice.shoppingmall.domain.coupon.exception.CouponNotFoundException;
import com.practice.shoppingmall.domain.coupon.exception.InvalidCouponException;
import com.practice.shoppingmall.domain.coupon.facade.CouponFacade;
import com.practice.shoppingmall.domain.item.domain.Item;
import com.practice.shoppingmall.domain.item.domain.repository.ItemRepository;
import com.practice.shoppingmall.domain.item.exception.NotEnoughStockException;
import com.practice.shoppingmall.domain.order.domain.Order;
import com.practice.shoppingmall.domain.order.domain.repository.OrderRepository;
import com.practice.shoppingmall.domain.order.presentation.dto.request.OrderItemRequest;
import com.practice.shoppingmall.domain.order.presentation.dto.request.OrderRequest;
import com.practice.shoppingmall.domain.order.presentation.dto.response.CreateOrderResponse;
import com.practice.shoppingmall.domain.order.service.OrderServiceImpl;
import com.practice.shoppingmall.domain.user.domain.User;
import com.practice.shoppingmall.domain.user.facade.UserFacade;
import com.practice.shoppingmall.global.error.exception.BusinessException;
import com.practice.shoppingmall.util.CouponBuilder;
import com.practice.shoppingmall.util.ItemBuilder;
import com.practice.shoppingmall.util.OrderBuilder;
import com.practice.shoppingmall.util.UserBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.fail;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private UserFacade userFacade;
    @Mock
    private ItemRepository itemRepository;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private CouponFacade couponFacade;
    @Mock
    private UserCouponRepository userCouponRepository;

    private static User user;
    private static Item item;
    private static UserCoupon userCoupon;
    private static Coupon coupon;
    private static Order order;

    @BeforeAll
    static void setUp() {
        user = UserBuilder.build();
        item = ItemBuilder.build();
        order = OrderBuilder.build();
    }

    @Test
    void 주문_성공() {
        //given
        Long orderItemId = OrderConstant.ORDER_ITEM_ID;
        int orderItemCount = OrderConstant.ORDER_ITEM_COUNT;

        given(userFacade.nowUser()).willReturn(user);
        given(itemRepository.findById(any())).willReturn(Optional.of(item));
        given(orderRepository.save(any())).willReturn(order);

        //when
        OrderItemRequest orderItemRequest = OrderItemRequest
                .builder()
                .itemId(orderItemId)
                .count(orderItemCount)
                .build();
        OrderRequest request = OrderRequest
                .builder()
                .orderItems(List.of(orderItemRequest))
                .build();

        CreateOrderResponse response = orderService.doOrder(request);

        //then
        assertThat(response.getId()).isNotNull();
        assertThat(response.getTotalPrice()).isEqualTo(item.getPrice() * orderItemRequest.getCount());
        verify(orderRepository, times(1)).save(any());
    }

    @Test
    void 주문_재고초과_실패() {
        //given
        Long orderItemId = OrderConstant.ORDER_ITEM_ID;
        int orderItemCount = ItemConstant.STOCK + 10;

        given(userFacade.nowUser()).willReturn(user);
        given(itemRepository.findById(any())).willReturn(Optional.of(item));

        try {
            OrderItemRequest orderItemRequest = OrderItemRequest
                    .builder()
                    .itemId(orderItemId)
                    .count(orderItemCount)
                    .build();
            OrderRequest request = OrderRequest
                    .builder()
                    .orderItems(List.of(orderItemRequest))
                    .build();
            CreateOrderResponse response = orderService.doOrder(request);
            fail("No error");
        } catch (BusinessException e) {
            //then
            assertThat(e).isInstanceOf(NotEnoughStockException.class);
            verify(orderRepository, times(0)).save(any());
        }
    }

    @Test
    void 주문_정액할인쿠폰_성공() {
        //given
        Long orderItemId = OrderConstant.ORDER_ITEM_ID;
        int orderItemCount = OrderConstant.ORDER_ITEM_COUNT;

        coupon = CouponBuilder.fixDiscountCouponBuild(CouponConstant.DISCOUNT_PRICE);
        userCoupon = CouponBuilder.userCouponBuild(coupon);
        Long userCouponId = CouponConstant.USER_COUPON_ID;

        given(userFacade.nowUser()).willReturn(user);
        given(itemRepository.findById(any())).willReturn(Optional.of(item));
        given(orderRepository.save(any(Order.class))).will(o -> o.getArgument(0));
        given(couponFacade.getUserCoupon(any(),any())).willReturn(userCoupon);
        given(couponFacade.validateCoupon(any())).willReturn(true);

        //when
        OrderItemRequest orderItemRequest = OrderItemRequest
                .builder()
                .itemId(orderItemId)
                .count(orderItemCount)
                .userCouponId(userCouponId)
                .build();
        OrderRequest request = OrderRequest
                .builder()
                .orderItems(List.of(orderItemRequest))
                .build();

        CreateOrderResponse response = orderService.doOrder(request);

        //then
        int notDiscountedTot = item.getPrice() * orderItemRequest.getCount();
        assertThat(response.getTotalPrice()).isEqualTo(notDiscountedTot - coupon.getDiscountPrice());
    }

    @Test
    void 주문_정액할인쿠폰_원가초과_0원() {
        //given
        Long orderItemId = OrderConstant.ORDER_ITEM_ID;
        int orderItemCount = OrderConstant.ORDER_ITEM_COUNT;

        coupon = CouponBuilder.fixDiscountCouponBuild(ItemConstant.PRICE * OrderConstant.ORDER_ITEM_COUNT + 1000);
        userCoupon = CouponBuilder.userCouponBuild(coupon);
        Long userCouponId = CouponConstant.USER_COUPON_ID;

        given(userFacade.nowUser()).willReturn(user);
        given(itemRepository.findById(any())).willReturn(Optional.of(item));
        given(orderRepository.save(any(Order.class))).will(o -> o.getArgument(0));
        given(couponFacade.getUserCoupon(any(),any())).willReturn(userCoupon);
        given(couponFacade.validateCoupon(any())).willReturn(true);

        //when
        OrderItemRequest orderItemRequest = OrderItemRequest
                .builder()
                .itemId(orderItemId)
                .count(orderItemCount)
                .userCouponId(userCouponId)
                .build();
        OrderRequest request = OrderRequest
                .builder()
                .orderItems(List.of(orderItemRequest))
                .build();

        CreateOrderResponse response = orderService.doOrder(request);

        //then
        assertThat(response.getTotalPrice()).isEqualTo(0);
    }

    @Test
    void 주문_비율할인쿠폰_성공() {
        //given
        Long orderItemId = OrderConstant.ORDER_ITEM_ID;
        int orderItemCount = OrderConstant.ORDER_ITEM_COUNT;

        coupon = CouponBuilder.rateDiscountCouponBuild();
        userCoupon = CouponBuilder.userCouponBuild(coupon);
        Long userCouponId = CouponConstant.USER_COUPON_ID;

        given(userFacade.nowUser()).willReturn(user);
        given(itemRepository.findById(any())).willReturn(Optional.of(item));
        given(orderRepository.save(any(Order.class))).will(o -> o.getArgument(0));
        given(couponFacade.getUserCoupon(any(),any())).willReturn(userCoupon);
        given(couponFacade.validateCoupon(any())).willReturn(true);
        //when
        OrderItemRequest orderItemRequest = OrderItemRequest
                .builder()
                .itemId(orderItemId)
                .count(orderItemCount)
                .userCouponId(userCouponId)
                .build();
        OrderRequest request = OrderRequest
                .builder()
                .orderItems(List.of(orderItemRequest))
                .build();

        CreateOrderResponse response = orderService.doOrder(request);

        //then
        int notDiscountedTot = item.getPrice() * orderItemRequest.getCount();
        assertThat(response.getTotalPrice())
                .isEqualTo(notDiscountedTot - (notDiscountedTot * coupon.getDiscountRate() / 100));
    }

    @Test
    void 주문_만료된_쿠폰_실패() {
        //given
        Long orderItemId = OrderConstant.ORDER_ITEM_ID;
        int orderItemCount = OrderConstant.ORDER_ITEM_COUNT;

        coupon = CouponBuilder.fixDiscountCouponBuild(CouponConstant.DISCOUNT_PRICE);
        userCoupon = CouponBuilder.userCouponBuild(coupon);
        Long userCouponId = CouponConstant.USER_COUPON_ID;

        given(userFacade.nowUser()).willReturn(user);
        given(itemRepository.findById(any())).willReturn(Optional.of(item));
        given(couponFacade.validateCoupon(any())).willReturn(false);

        try {
            OrderItemRequest orderItemRequest = OrderItemRequest
                    .builder()
                    .itemId(orderItemId)
                    .count(orderItemCount)
                    .userCouponId(userCouponId)
                    .build();
            OrderRequest request = OrderRequest
                    .builder()
                    .orderItems(List.of(orderItemRequest))
                    .build();
            CreateOrderResponse response = orderService.doOrder(request);
            fail("No error");
        } catch (BusinessException e) {
            //then
            assertThat(e).isInstanceOf(InvalidCouponException.class);
            verify(orderRepository, times(0)).save(any());
        }
    }

    @Test
    void 주문_소지X_쿠폰_실패() {
        //given
        Long orderItemId = OrderConstant.ORDER_ITEM_ID;
        int orderItemCount = OrderConstant.ORDER_ITEM_COUNT;

        coupon = CouponBuilder.fixDiscountCouponBuild(CouponConstant.DISCOUNT_PRICE);
        Long userCouponId = CouponConstant.USER_COUPON_ID;

        given(userFacade.nowUser()).willReturn(user);
        given(itemRepository.findById(any())).willReturn(Optional.of(item));
        given(couponFacade.getUserCoupon(any(),any())).willThrow(CouponNotFoundException.class);

        //when
        try {
            OrderItemRequest orderItemRequest = OrderItemRequest
                    .builder()
                    .itemId(orderItemId)
                    .count(orderItemCount)
                    .userCouponId(userCouponId)
                    .build();
            OrderRequest request = OrderRequest
                    .builder()
                    .orderItems(List.of(orderItemRequest))
                    .build();
            CreateOrderResponse response = orderService.doOrder(request);
            fail("No error");
        } catch (BusinessException e) {
            //then
            assertThat(e).isInstanceOf(CouponNotFoundException.class);
            verify(orderRepository, times(0)).save(any());
        }
    }

    @Test
    void 주문_취소_성공() {
        //given
        Long id = OrderConstant.ID;

        given(userFacade.nowUser()).willReturn(order.getUser());
        given(orderRepository.findById(any())).willReturn(Optional.of(order));

        //when
        orderService.cancelOrder(id);

        //then
        verify(orderRepository, times(1)).save(any());
    }
}