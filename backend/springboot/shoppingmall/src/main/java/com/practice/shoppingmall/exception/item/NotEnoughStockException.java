package com.practice.shoppingmall.exception.item;

import com.practice.shoppingmall.error.exception.BusinessException;
import com.practice.shoppingmall.error.exception.ErrorCode;

public class NotEnoughStockException extends BusinessException {
    public static final BusinessException EXCEPTION = new NotEnoughStockException();
    private NotEnoughStockException(){
        super(ErrorCode.NOT_ENOUGH_STOCK);
    }
}