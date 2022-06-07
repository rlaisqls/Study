package com.study.jwtlogin.controller;

import com.study.jwtlogin.dto.AccountDto;
import com.study.jwtlogin.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/register") //user 가입
    public AccountDto signup(@Valid @RequestBody AccountDto accountDto) { return accountService.register(accountDto); }

    @GetMapping("/user") //user 정보조회
    public AccountDto getMyInfo() {
        return accountService.getMyInfo();
    }

    @PatchMapping("/user") //user 정보 수정 (토큰 필요)
    public void updateMyInfo(@Valid @RequestBody AccountDto accountDto) {
        accountService.updateMyInfo(accountDto);
    }

    @GetMapping("/admin") //admin 전체 유저 정보조회 (토큰 필요)
    public List<AccountDto> getAllUserInfo() {
        return accountService.getAllUserInfo();
    }

    @GetMapping("/admin/{username}") //admin 유저 정보조회 (토큰 필요)
    public AccountDto getUserInfo(@PathVariable String username) {
        return accountService.getUserInfo(username);
    }
}