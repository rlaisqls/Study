package com.practice.shoppingmall.domain.item.exception;

import com.practice.shoppingmall.global.error.exception.BusinessException;
import com.practice.shoppingmall.global.error.exception.ErrorCode;

public class OutOfStockException extends BusinessException {
    public static final BusinessException EXCEPTION = new OutOfStockException();
    private OutOfStockException(){
        super(ErrorCode.OUT_OF_STOCK);
    }
}