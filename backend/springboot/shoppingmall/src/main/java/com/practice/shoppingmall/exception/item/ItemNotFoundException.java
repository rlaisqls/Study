package com.practice.shoppingmall.exception.item;


import com.practice.shoppingmall.global.error.exception.BusinessException;
import com.practice.shoppingmall.global.error.exception.ErrorCode;

public class ItemNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new ItemNotFoundException();
    public ItemNotFoundException(){
        super(ErrorCode.ITEM_NOT_FOUND);
    }
}