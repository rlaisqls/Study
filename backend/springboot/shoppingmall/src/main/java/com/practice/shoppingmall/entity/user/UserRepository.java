package com.practice.shoppingmall.entity.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findByUsername(String username);
}