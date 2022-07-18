package com.practice.shoppingmall.domain.auth.domain.repository;

import com.practice.shoppingmall.domain.auth.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}