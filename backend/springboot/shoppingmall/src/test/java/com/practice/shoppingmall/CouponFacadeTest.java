package com.practice.shoppingmall;

import com.practice.shoppingmall.constant.CouponConstant;
import com.practice.shoppingmall.domain.coupon.domain.Coupon;
import com.practice.shoppingmall.domain.coupon.domain.UserCoupon;
import com.practice.shoppingmall.domain.coupon.domain.repository.UserCouponRepository;
import com.practice.shoppingmall.domain.coupon.exception.CouponNotFoundException;
import com.practice.shoppingmall.domain.coupon.facade.CouponFacade;
import com.practice.shoppingmall.domain.user.domain.User;
import com.practice.shoppingmall.global.error.exception.BusinessException;
import com.practice.shoppingmall.util.CouponBuilder;
import com.practice.shoppingmall.util.UserBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.fail;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CouponFacadeTest {

    @InjectMocks
    private CouponFacade couponFacade;
    @Mock
    private UserCouponRepository userCouponRepository;

    private static User user;
    private static UserCoupon userCoupon;
    private static Coupon coupon;

    @BeforeAll
    static void setUp() {
        user = UserBuilder.build();
    }


    @Test
    void 유저_소지X_쿠폰_실패(){

        //given
        coupon = CouponBuilder.fixDiscountCouponBuild(CouponConstant.DISCOUNT_PRICE);
        userCoupon = CouponBuilder.userCouponBuild(coupon);
        Long userCouponId = CouponConstant.USER_COUPON_ID;

        given(userCouponRepository.findById(any())).willReturn(Optional.of(userCoupon));

        //when
        try {
            couponFacade.getUserCoupon(userCouponId, user);
            fail("No error");
        } catch (BusinessException e){
            //then
            assertThat(e).isInstanceOf(CouponNotFoundException.class);
        }
    }

    @Test
    void 만료된_쿠폰_실패(){
        //given
        coupon = CouponBuilder.fixDiscountCouponBuild(CouponConstant.DISCOUNT_PRICE);
        userCoupon = CouponBuilder.userCouponBuild(coupon);

        try (MockedStatic<LocalDateTime> localDateTimeMockedStatic = mockStatic(LocalDateTime.class)) {

            given(LocalDateTime.now()).willReturn(CouponConstant.COUPON_EXPIRED_DATE_TIME);

            //when
            Boolean response = couponFacade.validateCoupon(userCoupon);

            //then
            assertThat(response).isEqualTo(false);
            verify(userCouponRepository,times(1)).delete(any());
        }
    }
}