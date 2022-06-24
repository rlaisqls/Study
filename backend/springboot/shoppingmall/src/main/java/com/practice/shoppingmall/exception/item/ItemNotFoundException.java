package com.practice.shoppingmall.exception.item;


import com.practice.shoppingmall.error.exception.BusinessException;
import com.practice.shoppingmall.error.exception.ErrorCode;

public class ItemNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new ItemNotFoundException();
    private ItemNotFoundException(){
        super(ErrorCode.ITEM_NOT_FOUND);
    }
}