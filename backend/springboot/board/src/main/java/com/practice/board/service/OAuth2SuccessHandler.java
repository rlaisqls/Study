package com.practice.board.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.board.dto.response.TokensResponse;
import com.practice.board.entity.refeshToken.RefreshToken;
import com.practice.board.entity.refeshToken.RefreshTokenRepository;
import com.practice.board.entity.user.GoogleUser;
import com.practice.board.entity.user.User;
import com.practice.board.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Value("${jwt.exp.refresh}")
    private Long refreshTokenValidTime;
    private final ObjectMapper objectMapper;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
        System.out.println("  authentication.getPrincipal():   "+authentication.getPrincipal()+" authentication.getAuthorities(): "+authentication.getAuthorities()+", user.attribute:"+oAuth2User.getAuthorities());

        GoogleUser user = GoogleUser.builder()
                .oauthId(oAuth2User.getName())
                .username(oAuth2User.getAttributes().get("name").toString())
                .email(oAuth2User.getAttributes().get("email").toString())
                .build();

        RefreshToken refreshToken = refreshTokenRepository.save(
                RefreshToken.builder()
                        .uuid(String.valueOf(user.getUuid()))
                        .refreshToken(jwtTokenProvider.createRefreshToken(String.valueOf(user.getUuid())))
                        .expiration(refreshTokenValidTime)
                        .build());

        String targetUrl = UriComponentsBuilder.fromUriString("/login/check")
                .queryParam("accessToken", jwtTokenProvider.createAccessToken(String.valueOf(user.getUuid())))
                .queryParam("refreshToken", refreshToken)
                .build().toUriString();
        getRedirectStrategy().sendRedirect(request, response, targetUrl);

    }
}