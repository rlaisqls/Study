package com.study.jwtlogin.repository;

import com.study.jwtlogin.entity.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @EntityGraph(attributePaths = "authority")
        //Optional<Account> findOneWithAuthorityByUsername(String username);
        //WithAuthority의 의미가 뭘까...?
    Optional<Account> findByUsername(String username);
}