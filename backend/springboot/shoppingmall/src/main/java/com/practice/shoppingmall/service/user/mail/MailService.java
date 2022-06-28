package com.practice.shoppingmall.service.user.mail;

import com.practice.shoppingmall.dto.request.SendMailRequest;
import org.springframework.stereotype.Service;


public interface MailService {
    String sendEmail(SendMailRequest request);
}