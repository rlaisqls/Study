package com.study.jwtlogin.controller;

import com.study.jwtlogin.dto.AccountDto;
import com.study.jwtlogin.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/register")
    public AccountDto signup(@Valid @RequestBody AccountDto accountDto) {
        return accountService.register(accountDto);
    }

    @GetMapping("/user")
    public AccountDto getMyInfo(HttpServletRequest request) {
        return accountService.getMyInfo();
    }

    @GetMapping("/user/{username}")
    public AccountDto getUserInfo(@PathVariable String username) {
        return accountService.getUserInfo(username);
    }
}