package com.study.jwtlogin.service;

import com.study.jwtlogin.dto.AccountDto;
import com.study.jwtlogin.entity.Account;
import com.study.jwtlogin.entity.Authority;
import com.study.jwtlogin.repository.AccountRepository;
import com.study.jwtlogin.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    public AccountDto signup(AccountDto accountDto) {
        if (accountRepository.findOneWithAuthoritiesByUsername(accountDto.getUsername()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Account account = Account.builder()
                .username(accountDto.getUsername())
                .password(passwordEncoder.encode(accountDto.getPassword()))
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return AccountDto.from(accountRepository.save(account));
    }

    @Transactional(readOnly = true)
    public AccountDto getUserWithAuthorities(String username) {
        Account account = Account.builder()
                .username("no").build();
        return AccountDto.from(accountRepository.findOneWithAuthoritiesByUsername(username).orElse(account));
    }

    @Transactional(readOnly = true)
    public AccountDto getMyUserWithAuthorities() {
        return AccountDto.from(SecurityUtil.getCurrentUsername().flatMap(accountRepository::findOneWithAuthoritiesByUsername).orElse(null));
    }
}