package com.practice.shoppingmall.exception;

import com.practice.shoppingmall.error.exception.BusinessException;
import com.practice.shoppingmall.error.exception.ErrorCode;

public class MailSendFailException extends BusinessException {
    public static final BusinessException EXCEPTION = new MailSendFailException();
    private MailSendFailException(){
        super(ErrorCode.MAIL_SEND_FAIL);
    }
}