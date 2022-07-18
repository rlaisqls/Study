package com.practice.shoppingmall.domain.user.domain.repository;

import com.practice.shoppingmall.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findByEmail(String Email);

    Optional<User> findByUsername(String username);
}