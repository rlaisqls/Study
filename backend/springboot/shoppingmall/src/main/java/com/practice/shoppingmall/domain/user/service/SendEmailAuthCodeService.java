package com.practice.shoppingmall.domain.user.service;

import com.practice.shoppingmall.domain.user.domain.repository.UserRepository;
import com.practice.shoppingmall.domain.user.exception.UserAlreadyExistException;
import com.practice.shoppingmall.domain.user.facade.UserAuthCodeFacade;
import com.practice.shoppingmall.domain.user.presentation.dto.request.SendMailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SendEmailAuthCodeService {

    private final UserAuthCodeFacade authCodeFacade;

    private final UserRepository userRepository;

    @Transactional
    public void execute(SendMailRequest request){

        String email = request.getEmail();

        if(userRepository.findByEmail(email).isPresent())
            throw UserAlreadyExistException.EXCEPTION;

        authCodeFacade.sendEmail(email);
    }


}