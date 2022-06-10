package com.practice.board.controller;

import com.nimbusds.oauth2.sdk.TokenResponse;
import com.practice.board.service.user.OAuthUserService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Service
public class OAuthController {
    private final OAuthUserService oauthUserService;
}