package com.practice.shoppingmall.exception;


import com.practice.shoppingmall.error.exception.BusinessException;
import com.practice.shoppingmall.error.exception.ErrorCode;

public class ItemNotExistException extends BusinessException {
    public static final BusinessException EXCEPTION = new ItemNotExistException();
    public ItemNotExistException(){
        super(ErrorCode.ITEM_NOT_EXIST);
    }
}