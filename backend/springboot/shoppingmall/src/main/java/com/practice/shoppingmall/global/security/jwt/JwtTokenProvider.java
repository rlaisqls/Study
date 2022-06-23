package com.practice.shoppingmall.global.security.jwt;


import com.practice.shoppingmall.dto.response.TokenResponse;
import com.practice.shoppingmall.entity.refreshtoken.RefreshToken;
import com.practice.shoppingmall.entity.refreshtoken.RefreshTokenRepository;
import com.practice.shoppingmall.exception.ExpiredTokenException;
import com.practice.shoppingmall.exception.InvalidTokenException;
import com.practice.shoppingmall.global.security.auth.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider implements InitializingBean {

    private final JwtProperties jwtProperties;

    private final CustomUserDetailsService customUserDetailsService;

    private final RefreshTokenRepository refreshTokenRepository;

    private Key key;

    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecret());
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createAccessToken(String uuid) {
        Date now = new Date();
        String accessToken = Jwts.builder()
                .setSubject(uuid)
                .claim("type", "access")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtProperties.getAccess() * 1000))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return accessToken;
    }

    public String createRefreshToken(String uuid) {
        Date now = new Date();
        String refreshToken = Jwts.builder()
                .setSubject(uuid)
                .claim("type", "refresh")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtProperties.getRefresh() * 1000))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        refreshTokenRepository.save(RefreshToken.builder()
                .uuid(uuid)
                .refreshToken(refreshToken)
                .expiration(jwtProperties.getRefresh() * 1000).build());

        return refreshToken;
    }

    public TokenResponse createTokens(String uuid) {
        return new TokenResponse(createAccessToken(uuid), createRefreshToken(uuid));
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getId(String token) {
        return getClaims(token).getSubject();
    }

    public boolean isRefreshToken(String token) {
        return getClaims(token).get("type").equals("refresh");
    }

    private Claims getClaims(String token) {
        try {
            return Jwts
                    .parser()
                    .setSigningKey(key)
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
            return bearerToken.substring(7);
        }
        return null;
    }

}