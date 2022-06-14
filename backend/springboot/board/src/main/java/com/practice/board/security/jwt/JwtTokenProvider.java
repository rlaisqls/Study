package com.practice.board.security.jwt;

import com.practice.board.exception.ExpiredAccessTokenException;
import com.practice.board.exception.ExpiredRefreshTokenException;
import com.practice.board.exception.IncorrectTokenException;
import com.practice.board.exception.InvalidTokenException;
import com.practice.board.setting.SecurityProperty;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
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

    private final SecurityProperty securityProperty;

    private final CustomUserDetailsService customUserDetailsService;
    private Key key;

    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(securityProperty.getSecret());
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createAccessToken(String uuid) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(uuid)
                .claim("type", "access") //엑세스토큰임을 명시하는 정보 추가
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + securityProperty.getExp().getToken()))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public String createRefreshToken(String uuid){
        Date now = new Date();
        return Jwts.builder()
                .setSubject(uuid)
                .claim("type", "refresh") //리프레시 토큰임을 명시하는 정보 추가
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + securityProperty.getExp().getRefresh()))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts
                .parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(claims.getSubject()); //
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        //스프링 시큐리티에서 인증할때 쓰이는 형태의 토큰으로 변환해서 return
    }

    public String getId(String token){
        try{
            return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
        } catch (MalformedJwtException | UnsupportedJwtException e) {
            throw IncorrectTokenException.EXCEPTION;
        } catch (ExpiredJwtException e) {
            throw ExpiredAccessTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(securityProperty.getHeader());
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean isRefreshToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody().get("type").equals("refresh");
        } catch (MalformedJwtException | UnsupportedJwtException e) {
            throw IncorrectTokenException.EXCEPTION;
        } catch (ExpiredJwtException e) {
            throw ExpiredRefreshTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }
}