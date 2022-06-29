package com.practice.shoppingmall.service.mail;

import com.practice.shoppingmall.dto.request.SendMailRequest;
import com.practice.shoppingmall.exception.MailSendFailException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromAddress;

    @Override
    public void sendEmail(SendMailRequest request) {
        try {
            sendMailLogic(request);
        } catch (Exception e) {
            throw MailSendFailException.EXCEPTION;
        }
    }

    private void sendMailLogic(SendMailRequest request) throws Exception {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, false, "UTF-8");

        messageHelper.setTo(request.getAddress());
        messageHelper.setFrom(fromAddress);
        messageHelper.setSubject(request.getTitle());


        messageHelper.setText(request.getAuthenticationCode()+"를 입력하세요");

        mailSender.send(message);
    }
}