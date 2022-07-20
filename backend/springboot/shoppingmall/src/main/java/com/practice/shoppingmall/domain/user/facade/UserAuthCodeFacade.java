package com.practice.shoppingmall.domain.user.facade;

import com.practice.shoppingmall.domain.user.domain.AuthCode;
import com.practice.shoppingmall.domain.user.domain.repository.AuthCodeRepository;
import com.practice.shoppingmall.domain.user.exception.BadAuthCodeException;
import com.practice.shoppingmall.global.util.JmsUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@RequiredArgsConstructor
@Component
public class UserAuthCodeFacade {

    private final AuthCodeRepository authCodeRepository;

    private final JmsUtil jmsUtil;

    @Value("${auth.code-exp}")
    private long authCodeExp;

    public static final Random RANDOM = new Random();

    public void sendEmail(String email){

        String code = createRandomCode();
        AuthCode authCode = getAuthCodeByIdOrCreate(email, code);

        jmsUtil.sendMassage(email, code);

        authCode.updateAuthCode(code, authCodeExp);
    }

    private AuthCode getAuthCodeByIdOrCreate(String email, String code) {
        return authCodeRepository.findById(email)
                .orElseGet(() -> buildAuthCode(email, code));
    }

    private AuthCode buildAuthCode(String email, String code) {
        return authCodeRepository.save(AuthCode.builder()
                .email(email)
                .code(code)
                .isVerified(false)
                .timeToLive(authCodeExp)
                .build());
    }

    private String createRandomCode() {
        return String.format("%06d", RANDOM.nextInt(1000000) % 1000000);
    }

    public AuthCode getAuthCodeById(String email) {
        return authCodeRepository.findById(email)
                .orElseThrow(() -> BadAuthCodeException.EXCEPTION);
    }

    public boolean isVerified(String email) {
        return getAuthCodeById(email).isVerified();
    }
}