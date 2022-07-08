package com.practice.shoppingmall.domain.order.exception;

import com.practice.shoppingmall.global.error.exception.BusinessException;
import com.practice.shoppingmall.global.error.exception.ErrorCode;

public class AlreadyDeliveredException extends BusinessException {
    public static final BusinessException EXCEPTION = new AlreadyDeliveredException();
    private AlreadyDeliveredException(){
        super(ErrorCode.ALREADY_DELIVERED);
    }
}