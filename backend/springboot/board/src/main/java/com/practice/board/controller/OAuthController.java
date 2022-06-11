package com.practice.board.controller;

import com.practice.board.service.OAuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OAuthController {
    private final OAuthUserService oauthUserService;
}