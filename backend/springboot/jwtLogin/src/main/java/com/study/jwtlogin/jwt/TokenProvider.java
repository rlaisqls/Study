package com.study.jwtlogin.jwt;

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
public class TokenProvider implements InitializingBean {

    @Value("${jwt.secret}") private String secret;
    @Value("${jwt.exp.token}") private Long tokenValidTime;
    @Value("${jwt.exp.refresh}")private Long refreshTokenValidTime;
    @Value("${jwt.header}") public static final String AUTHORIZATION_HEADER = "Authorization";
    private final CustomUserDetailsService customUserDetailsService;
    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
    private Key key;

    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createAccessToken(String username) {
        /* 원래 여기서 파라미터를 Authentication 클래스로 받은 다음에
         * authorities 정보를 claim으로 같이 토큰에 넣어줬었는데
         * 나중에 UserDetailService에서
         * loadUserByUsername 메서드로 찾아서 쓸거라서 그냥 유저 네임만 넣어줌*/
        Date now = new Date();
        return Jwts.builder()
                .setSubject(username)
                .claim("type", "access") //엑세스토큰임을 명시하는 정보 추가
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public String createRefreshToken(String username){
        Date now = new Date();
        return Jwts.builder()
                .setSubject(username)
                .claim("type", "refresh") //리프레시 토큰임을 명시하는 정보 추가
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + refreshTokenValidTime))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(claims.getSubject()); //
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        //스프링 시큐리티에서 인증할때 쓰이는 형태의 토큰으로 변환해서 return
    }

    public String getUsername(String token){
        try{
            return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
        }catch (Exception e){
            throw new IllegalStateException(); //토큰에 유저아이디가 없음, 인증 안된토큼임
        }
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean isRefreshToken(String token) {
        try {
            return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().get("type").equals("refresh");
        } catch (Exception e) {
            return false;
        }
    }
}