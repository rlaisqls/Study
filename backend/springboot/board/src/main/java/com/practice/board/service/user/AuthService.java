package com.practice.board.service.user;

import com.practice.board.dto.request.LoginRequest;
import com.practice.board.dto.response.TokensResponse;
import com.practice.board.entity.refeshToken.RefreshTokenRepository;
import com.practice.board.entity.user.User;
import com.practice.board.entity.user.UserRepository;
import com.practice.board.exception.IncorrectTokenException;
import com.practice.board.exception.InvalidInformationException;
import com.practice.board.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AuthService {

    @Value("${jwt.exp.refresh}")
    private Long refreshTokenValidTime;

    private final LoginService loginService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public TokensResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .filter(a -> passwordEncoder.matches(request.getPassword(),a.getPassword())) //패스워드 입력과 데이터 비교
                .orElseThrow(InvalidInformationException::new);
        return loginService.login(user);
    }

    @Transactional
    public TokensResponse reissue(String refreshToken) {
        if(!jwtTokenProvider.isRefreshToken(refreshToken)) throw new IncorrectTokenException();
        String uuid = jwtTokenProvider.getId(refreshToken);

        refreshTokenRepository.findById(uuid)
                .filter(token -> token.getRefreshToken().equals(refreshToken)) //저장한 토큰과 동일한지 확인
                .map(token -> token.updateExpiration(refreshTokenValidTime)) //유효시간 갱신
                .orElseThrow(IncorrectTokenException::new);

        return TokensResponse.builder()
                .accessToken(jwtTokenProvider.createAccessToken(uuid))
                .refreshToken(refreshToken)
                .build();
    }

}