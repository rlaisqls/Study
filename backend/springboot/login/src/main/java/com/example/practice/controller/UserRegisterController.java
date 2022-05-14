package com.example.practice.controller;

import com.example.practice.dto.AccountDTO;
import com.example.practice.dto.RegisterDTO;
import com.example.practice.repository.AccountRepository;
import com.example.practice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserRegisterController {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountService accountService;
    @GetMapping(value = "/")
    public String home() {
        return "home.html";
    }
    @GetMapping(value = "/register")
    public String registerForm() {
        return "registerForm.html";
    }
    @PostMapping(value = "/register/new")
    public String register(RegisterDTO registerDTO) {
        accountService.register(registerDTO);
        return "redirect:/";
    }

    @GetMapping(value = "/login")
    public String loginForm() {  return "login.html"; }

    @PostMapping(value = "/login/success")
    public String login(AccountDTO accountDTO) {
        accountService.login(accountDTO);
        return "redirect:/";
    }
}