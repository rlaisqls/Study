package com.practice.shoppingmall.global.security.jwt;


import com.practice.shoppingmall.domain.auth.domain.RefreshToken;
import com.practice.shoppingmall.domain.auth.domain.repository.RefreshTokenRepository;
import com.practice.shoppingmall.domain.auth.exception.ExpiredTokenException;
import com.practice.shoppingmall.domain.auth.exception.InvalidTokenException;
import com.practice.shoppingmall.global.security.auth.AuthDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;

    private final RefreshTokenRepository refreshTokenRepository;

    private final AuthDetailsService authDetailsService;

    public String createAccessToken(String username) {

        return createToken(username, "access", jwtProperties.getAccessExp());
    }

    public String createRefreshToken(String username) {

        String refreshToken = createToken(username, "refresh", jwtProperties.getRefreshExp());

        refreshTokenRepository.save(RefreshToken.builder()
                .username(username)
                .refreshToken(refreshToken)
                .expiration(jwtProperties.getRefreshExp() * 1000)
                .build());

        return refreshToken;
    }

    private String createToken(String username, String typ, Long exp) {

        return Jwts.builder()
                .setSubject(username)
                .claim("typ", typ)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
                .setIssuedAt(new Date())
                .compact();
    }

    public Authentication getAuthentication(String token) {

        if(isRefreshToken(token))
            throw InvalidTokenException.EXCEPTION;

        UserDetails userDetails = authDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public boolean isRefreshToken(String token) {
        return getClaims(token).get("typ").equals("refresh");
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    public ZonedDateTime getExpiredTime() {
        return ZonedDateTime.now().plusSeconds(jwtProperties.getAccessExp());
    }

    private Claims getClaims(String token) {
        try {
            return Jwts
                    .parser()
                    .setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(jwtProperties.getHeader());

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(jwtProperties.getPrefix())
                && bearerToken.length() > jwtProperties.getPrefix().length() + 1) {
            return bearerToken.substring(jwtProperties.getPrefix().length() + 1);
        }
        return null;
    }

}