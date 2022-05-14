package com.example.practice.repository;

import com.example.practice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {

    public Account findByUsername(String username);
    public Account findByUsernameAndPassword(String username, String password);
}