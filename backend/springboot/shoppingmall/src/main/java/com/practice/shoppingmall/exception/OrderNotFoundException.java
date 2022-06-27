package com.practice.shoppingmall.exception;

import com.practice.shoppingmall.error.exception.BusinessException;
import com.practice.shoppingmall.error.exception.ErrorCode;

public class OrderNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new OrderNotFoundException();
    private OrderNotFoundException(){
        super(ErrorCode.ORDER_NOT_FOUND);
    }
}