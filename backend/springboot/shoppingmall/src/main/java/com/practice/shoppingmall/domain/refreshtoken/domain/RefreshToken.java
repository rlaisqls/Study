package com.practice.shoppingmall.domain.refreshtoken.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Builder
@RedisHash
public class RefreshToken { //Serializable를 상속받으면 Jdk 직렬화를 사용함
    @Id
    private String username;

    @Indexed
    private String refreshToken;

    @TimeToLive
    private Long expiration;

    public RefreshToken updateExpiration(Long expiration) {
        this.expiration = expiration;
        return this;
    }
}