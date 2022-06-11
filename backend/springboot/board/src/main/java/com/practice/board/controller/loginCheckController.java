package com.practice.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class loginCheckController {
    @GetMapping("/login/check")
    public String loginCheck(@RequestParam String accessToken, @RequestParam String refreshToken){
        /* OAuth2SuccessHandler에서 구글 OAuth로 로그인 확인 한 후에
         토큰을 json으로 response에 담아서 페이지로 반환하는 방법을 모르겠어서 일단 쿼리 파라미터로 볼 수 있게 해놓음*/
        return "redirect:/";
    }
}