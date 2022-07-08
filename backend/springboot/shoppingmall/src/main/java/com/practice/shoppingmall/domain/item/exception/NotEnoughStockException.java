package com.practice.shoppingmall.domain.item.exception;

import com.practice.shoppingmall.global.error.exception.BusinessException;
import com.practice.shoppingmall.global.error.exception.ErrorCode;

public class NotEnoughStockException extends BusinessException {
    public static final BusinessException EXCEPTION = new NotEnoughStockException();
    private NotEnoughStockException(){
        super(ErrorCode.NOT_ENOUGH_STOCK);
    }
}