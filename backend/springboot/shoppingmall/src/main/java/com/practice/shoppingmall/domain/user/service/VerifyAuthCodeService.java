package com.practice.shoppingmall.domain.user.service;

import com.practice.shoppingmall.domain.user.domain.AuthCode;
import com.practice.shoppingmall.domain.user.domain.repository.AuthCodeRepository;
import com.practice.shoppingmall.domain.user.exception.BadAuthCodeException;
import com.practice.shoppingmall.domain.user.facade.UserAuthCodeFacade;
import com.practice.shoppingmall.domain.user.presentation.dto.request.VerifyAuthCodeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class VerifyAuthCodeService {

    private final UserAuthCodeFacade userAuthCodeFacade;

    private final AuthCodeRepository authCodeRepository;

    private final UserAuthCodeFacade authCodeFacade;

    @Transactional
    public void execute(VerifyAuthCodeRequest request) {

        String email = request.getEmail();
        String code = request.getCode();

        AuthCode authCode = authCodeFacade.getAuthCodeById(email);

        if(authCode.isVerified()||!code.equals(authCode.getCode()))
            throw BadAuthCodeException.EXCEPTION;

        authCode.verify();

        authCodeRepository.save(authCode);
    }

}