package com.practice.shoppingmall.service.mail;

import com.practice.shoppingmall.dto.request.SendMailRequest;


public interface MailService {
    void sendEmail(SendMailRequest request);
}