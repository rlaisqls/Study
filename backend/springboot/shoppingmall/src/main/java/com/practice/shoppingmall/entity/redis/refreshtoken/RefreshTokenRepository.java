package com.practice.shoppingmall.entity.redis.refreshtoken;

import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}