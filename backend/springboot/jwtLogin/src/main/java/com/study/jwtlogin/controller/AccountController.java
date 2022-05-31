package com.study.jwtlogin.controller;

import com.study.jwtlogin.dto.AccountDto;
import com.study.jwtlogin.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class AccountController {
    private final AccountService accountService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping("/register")
    public ResponseEntity<AccountDto> signup(@Valid @RequestBody AccountDto accountDto) {
        return ResponseEntity.ok(accountService.signup(accountDto));
    }
    @GetMapping("/user")
    public ResponseEntity<AccountDto> getMyUserInfo(HttpServletRequest request) {
        return ResponseEntity.ok(accountService.getMyUserWithAuthorities());
    }
    @GetMapping("/user/{username}")
    public ResponseEntity<AccountDto> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok(accountService.getUserWithAuthorities(username));
    }
}