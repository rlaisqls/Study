package com.practice.shoppingmall.domain.user.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@Builder
@RedisHash
public class AuthCode {

    @Id
    private final String email;

    private String code;

    private boolean isVerified;

    @TimeToLive
    private long timeToLive;

    public void updateAuthCode(String code, long timeToLive) {
        this.code = code;
        this.timeToLive = timeToLive;
        this.isVerified = false;
    }

    public AuthCode verify() {
        this.isVerified = true;
        return this;
    }
}