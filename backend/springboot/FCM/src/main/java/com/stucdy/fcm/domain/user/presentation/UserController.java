package com.stucdy.fcm.domain.user.presentation;

import com.stucdy.fcm.domain.auth.presentation.dto.TokenResponse;
import com.stucdy.fcm.domain.user.presentation.dto.request.SignUpRequest;
import com.stucdy.fcm.domain.user.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final SignUpService signUpService;

    @PostMapping("/")
    public TokenResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return signUpService.execute(request);
    }

    @PostMapping("/auth")
    public TokenResponse signIn(@RequestBody @Valid SignUpRequest request) {
        return signUpService.execute(request);
    }

}