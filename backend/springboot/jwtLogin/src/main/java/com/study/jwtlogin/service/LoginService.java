package com.study.jwtlogin.service;

import com.study.jwtlogin.dto.LoginDto;
import com.study.jwtlogin.dto.TokensDto;
import com.study.jwtlogin.entity.Account;
import com.study.jwtlogin.entity.RefreshToken;
import com.study.jwtlogin.jwt.TokenProvider;
import com.study.jwtlogin.repository.AccountRepository;
import com.study.jwtlogin.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {

    @Value("${jwt.exp.refresh}") private Long refreshTokenValidTime;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public TokensDto login(LoginDto loginDto) {
        accountRepository.findByUsername(loginDto.getUsername())
                .filter(a -> passwordEncoder.matches(loginDto.getPassword(),a.getPassword())) //패스워드 입력과 데이터 비교
                .orElseThrow(IllegalStateException::new);
        RefreshToken refreshToken = RefreshToken.builder()
                .username(loginDto.getUsername())
                .refreshToken(tokenProvider.createRefreshToken(loginDto.getUsername()))
                .expiration(refreshTokenValidTime)
                .build();
        System.out.println("*************"+loginDto.getUsername()+" "+loginDto.getPassword());

        refreshTokenRepository.save(refreshToken);
        System.out.println("로그인 할때 생성한 리프레시 토큰 : "+refreshToken.getRefreshToken());
        System.out.println("&&&&&&&&&&&&&&위에서 getUsername"+tokenProvider.getUsername(refreshToken.getRefreshToken()));
        return TokensDto.builder()
                .refreshToken(refreshToken.getRefreshToken()) //리프레시 토큰
                .accessToken(tokenProvider.createAccessToken(loginDto.getUsername())) //엑세스 토큰 생성
                .build();
    }

    @Transactional
    public TokensDto reissue(String refreshToken) {
        String username = tokenProvider.getUsername(refreshToken);
        if(!tokenProvider.isRefreshToken(refreshToken)) throw new IllegalStateException("");

        RefreshToken newRefreshToken = refreshTokenRepository.findById(username)
                .filter(token -> token.getRefreshToken().equals(refreshToken)) //저장한 토큰과 동일한지 확인
                .orElseThrow(IllegalStateException::new);

        refreshTokenRepository.findByRefreshToken(refreshToken)
                .map(refresh -> refresh.updateExpiration(refreshTokenValidTime)) //유효시간 갱신
                .orElseThrow(IllegalStateException::new);

        return new TokensDto(tokenProvider.createAccessToken(newRefreshToken.getUsername()),refreshToken);
    }
}