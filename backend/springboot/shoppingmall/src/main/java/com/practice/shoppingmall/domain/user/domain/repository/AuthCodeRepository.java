package com.practice.shoppingmall.domain.user.domain.repository;

import com.practice.shoppingmall.domain.user.domain.AuthCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthCodeRepository extends JpaRepository<AuthCode, String> {
}