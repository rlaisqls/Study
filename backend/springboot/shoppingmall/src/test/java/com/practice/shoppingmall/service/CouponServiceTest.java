package com.practice.shoppingmall.service;

import com.practice.shoppingmall.constant.CouponConstant;
import com.practice.shoppingmall.domain.coupon.domain.Coupon;
import com.practice.shoppingmall.domain.coupon.domain.CouponDiscountType;
import com.practice.shoppingmall.domain.coupon.domain.repository.CouponRepository;
import com.practice.shoppingmall.domain.coupon.exception.DiscountOutOfRangeException;
import com.practice.shoppingmall.domain.coupon.presentation.dto.request.CreateCouponRequest;
import com.practice.shoppingmall.domain.coupon.presentation.dto.response.CreateCouponResponse;
import com.practice.shoppingmall.domain.coupon.service.CouponServiceImpl;
import com.practice.shoppingmall.global.error.exception.BusinessException;
import com.practice.shoppingmall.util.CouponBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CouponServiceTest {
    @InjectMocks
    private CouponServiceImpl couponService;

    @Mock
    private CouponRepository couponRepository;

    private static Coupon coupon;

    @Test
    void 정액할인_쿠폰_생성_성공(){
        //given
        coupon = CouponBuilder.fixDiscountCouponBuild(CouponConstant.DISCOUNT_PRICE);

        String name = coupon.getCouponName();
        CouponDiscountType discountType = coupon.getDiscountType();
        Integer discountAmount = coupon.getDiscountPrice();
        Long validityPeriod = coupon.getValidityPeriod();

        given(couponRepository.save(any())).willReturn(CouponBuilder.fixDiscountCouponBuild(CouponConstant.DISCOUNT_PRICE));

        //when
        CreateCouponRequest request = CreateCouponRequest
                .builder()
                .name(name)
                .discountType(discountType)
                .discountAmount(discountAmount)
                .validityPeriod(validityPeriod)
                .build();
        CreateCouponResponse response = couponService.createCoupon(request);

        //then
        assertThat(response.getId()).isNotNull();
        verify(couponRepository, times(1)).save(any());
    }

    @Test
    void 비율할인_쿠폰_생성_성공() {
        //given
        coupon = CouponBuilder.rateDiscountCouponBuild();

        String name = coupon.getCouponName();
        CouponDiscountType discountType = coupon.getDiscountType();
        Integer discountAmount = coupon.getDiscountRate();
        Long validityPeriod = coupon.getValidityPeriod();

        given(couponRepository.save(any())).willReturn(CouponBuilder.fixDiscountCouponBuild(CouponConstant.DISCOUNT_PRICE));

        //when
        CreateCouponRequest request = CreateCouponRequest
                .builder()
                .name(name)
                .discountType(discountType)
                .discountAmount(discountAmount)
                .validityPeriod(validityPeriod)
                .build();
        CreateCouponResponse response = couponService.createCoupon(request);

        //then
        assertThat(response.getId()).isNotNull();
        verify(couponRepository, times(1)).save(any());
    }

    @Test
    void 비율할인_쿠폰_생성_실패() {
        //given
        coupon = CouponBuilder.rateDiscountCouponBuild();

        String name = coupon.getCouponName();
        CouponDiscountType discountType = coupon.getDiscountType();
        Integer discountAmount = 200;
        Long validityPeriod = coupon.getValidityPeriod();

        //when
        try {
            CreateCouponRequest request = CreateCouponRequest
                    .builder()
                    .name(name)
                    .discountType(discountType)
                    .discountAmount(discountAmount)
                    .validityPeriod(validityPeriod)
                    .build();
            CreateCouponResponse response = couponService.createCoupon(request);
        } catch (BusinessException e){
            //then
            assertThat(e).isInstanceOf(DiscountOutOfRangeException.class);
            verify(couponRepository, times(0)).save(any());
        }
    }

}