package com.practice.board.controller;

import com.practice.board.dto.response.TokensResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class loginCheckController {
    @GetMapping("/login/check")
    public String loginCheck(@RequestParam String accessToken, @RequestParam String refreshToken){
        return "redirect:/";
    }
}