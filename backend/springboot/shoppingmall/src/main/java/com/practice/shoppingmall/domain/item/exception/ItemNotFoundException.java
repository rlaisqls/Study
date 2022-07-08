package com.practice.shoppingmall.domain.item.exception;


import com.practice.shoppingmall.global.error.exception.BusinessException;
import com.practice.shoppingmall.global.error.exception.ErrorCode;

public class ItemNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new ItemNotFoundException();
    private ItemNotFoundException(){
        super(ErrorCode.ITEM_NOT_FOUND);
    }
}