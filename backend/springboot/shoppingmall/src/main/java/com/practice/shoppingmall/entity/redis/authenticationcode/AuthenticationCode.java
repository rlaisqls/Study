package com.practice.shoppingmall.entity.redis.authenticationcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Getter
@AllArgsConstructor
@RedisHash(timeToLive = 60 * 5)
public class AuthenticationCode {

    @Id
    private final String email;

    private final String code;
}