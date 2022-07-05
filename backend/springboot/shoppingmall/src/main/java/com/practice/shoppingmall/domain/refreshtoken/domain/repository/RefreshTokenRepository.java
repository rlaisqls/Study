package com.practice.shoppingmall.domain.refreshtoken.domain.repository;

import com.practice.shoppingmall.domain.refreshtoken.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}