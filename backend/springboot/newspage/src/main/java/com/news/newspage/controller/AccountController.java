package com.news.newspage.controller;

import com.news.newspage.dto.AccountDTO;
import com.news.newspage.dto.RegisterDTO;
import com.news.newspage.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.UnsupportedEncodingException;

@Controller
public class AccountController {
    @Autowired AccountService accountService;
    @GetMapping(value = "/register")
    public String registerForm() {
        return "account/registerForm.jsp";
    }
    @PostMapping(value = "/register/new")
    public String register(RegisterDTO registerDTO, Model model) throws UnsupportedEncodingException {
        String result = accountService.register(registerDTO);
        if(result.equals("success")){
            model.addAttribute("error", false);
            return "redirect:/";
        }else{
            model.addAttribute("error", true);
            model.addAttribute("exception", result);
            return "redirect:/register";
        }
    }
    @GetMapping(value = "/login")
    public String loginForm() {  return "account/loginForm.jsp"; }
    @PostMapping(value = "/login/account")
    public String login(AccountDTO accountDTO) {
        String result = accountService.login(accountDTO);
        if(result.length()==0){
            return "redirect:/";
        }else{
            return "redirect:/login";
        }
    }
}


