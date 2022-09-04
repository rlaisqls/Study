package com.stucdy.fcm.global.security.jwt;

import com.stucdy.fcm.domain.auth.domain.RefreshToken;
import com.stucdy.fcm.domain.auth.domain.repository.RefreshTokenRepository;
import com.stucdy.fcm.global.exception.ExpiredTokenException;
import com.stucdy.fcm.global.exception.InvalidTokenException;
import com.stucdy.fcm.global.security.auth.AuthDetailsService;
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
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider{

    private final JwtProperties jwtProperties;
    private final AuthDetailsService authDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;

    public String createAccessToken(String accountId) {
        return createToken(accountId, "access", jwtProperties.getAccessExp());
    }

    public String createRefreshToken(String accountId) {

        String refreshToken = createToken(accountId, "refresh", jwtProperties.getRefreshExp());

        refreshTokenRepository.save(RefreshToken.builder()
                .accountId(accountId)
                .token(refreshToken)
                .expiration(jwtProperties.getRefreshExp() * 1000)
                .build());

        return refreshToken;
    }

    private String createToken(String accountId, String typ, Long exp) {
        return Jwts.builder()
                .setSubject(accountId)
                .claim("typ", typ)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
                .setIssuedAt(new Date())
                .compact();
    }

    public Authentication getAuthentication(String token) {

        UserDetails userDetails = authDetailsService.loadUserByUsername(getAccountId(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getAccountId(String token) {
        return getClaims(token).getSubject();
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