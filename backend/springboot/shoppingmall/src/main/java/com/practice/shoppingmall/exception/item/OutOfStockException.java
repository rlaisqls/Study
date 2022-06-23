package com.practice.shoppingmall.exception.item;

import com.practice.shoppingmall.global.error.exception.BusinessException;
import com.practice.shoppingmall.global.error.exception.ErrorCode;

public class OutOfStockException extends BusinessException {
    public static final BusinessException EXCEPTION = new OutOfStockException();
    public OutOfStockException(){
        super(ErrorCode.OUT_OF_STOCK);
    }
}