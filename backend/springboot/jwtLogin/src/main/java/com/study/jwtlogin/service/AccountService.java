package com.study.jwtlogin.service;

import com.study.jwtlogin.dto.AccountDto;
import com.study.jwtlogin.entity.Account;
import com.study.jwtlogin.entity.Authority;
import com.study.jwtlogin.exception.UserAlreadyExistException;
import com.study.jwtlogin.exception.UserNotFoundException;
import com.study.jwtlogin.repository.AccountRepository;
import com.study.jwtlogin.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AccountDto register(AccountDto accountDto) {
        if(accountRepository.findByUsername(accountDto.getUsername()).orElse(null) != null) {
            throw new UserAlreadyExistException();
        }
        Account account = Account.builder()
                .username(accountDto.getUsername())
                .password(passwordEncoder.encode(accountDto.getPassword()))
                .authority(Authority.valueOf("ROLE_USER"))
                .activated(true)
                .build();

        return AccountDto.from(accountRepository.save(account));
    }

    @Transactional(readOnly = true)
    public AccountDto getUserInfo(String username) {
        return AccountDto.from(accountRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new));
    }

    @Transactional(readOnly = true)
    public List<AccountDto> getAllUserInfo() {
        return accountRepository.findAll().stream()
                .map(AccountDto::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AccountDto getMyInfo() {
        return AccountDto.from(SecurityUtil.getCurrentUsername().flatMap(accountRepository::findByUsername)
                .orElseThrow(UserNotFoundException::new));
    }

    public void updateMyInfo(AccountDto accountDto) {
        Account account = SecurityUtil.getCurrentUsername().flatMap(accountRepository::findByUsername)
                .orElseThrow(UserNotFoundException::new);
        account.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        accountRepository.save(account);
    }
}