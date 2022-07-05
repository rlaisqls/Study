package com.practice.shoppingmall.global.exception;

import com.practice.shoppingmall.global.error.exception.BusinessException;
import com.practice.shoppingmall.global.error.exception.ErrorCode;

public class MailSendFailException extends BusinessException {
    public static final BusinessException EXCEPTION = new MailSendFailException();
    private MailSendFailException(){
        super(ErrorCode.MAIL_SEND_FAIL);
    }
}