package com.practice.shoppingmall.global.util;

import com.practice.shoppingmall.domain.user.exception.MailSendFailException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
@RequiredArgsConstructor
public class JmsUtil {

    @Value("${spring.mail.username}")
    private String fromAddress;

    private final JavaMailSender mailSender;

    public void sendMassage(String email, String code) {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, false, "UTF-8");

            messageHelper.setTo(email);
            messageHelper.setFrom(fromAddress);
            messageHelper.setSubject("이메일 인증");
            messageHelper.setText(code + "를 입력하세요");

            mailSender.send(message);
        } catch (Exception e){
            throw MailSendFailException.EXCEPTION;
        }

    }
}