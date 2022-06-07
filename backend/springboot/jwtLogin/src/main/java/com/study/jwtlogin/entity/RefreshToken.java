package com.study.jwtlogin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Getter
@Builder
@AllArgsConstructor
@RedisHash
public class RefreshToken implements Serializable {
    @Id
    private String username;

    @Indexed
    private String refreshToken;

    @TimeToLive //객체가 redis에 저장된 후 시간이 지나면 엔티티가 삭제된다?
    private Long expiration;

    public RefreshToken updateExpiration(Long expiration) {
        this.expiration = expiration;
        return this;
    }
}