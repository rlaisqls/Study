package com.practice.shoppingmall.exception.item;

import com.practice.shoppingmall.error.exception.BusinessException;
import com.practice.shoppingmall.error.exception.ErrorCode;

public class OutOfStockException extends BusinessException {
    public static final BusinessException EXCEPTION = new OutOfStockException();
    private OutOfStockException(){
        super(ErrorCode.OUT_OF_STOCK);
    }
}