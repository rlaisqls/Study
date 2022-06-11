package com.project.dcb.controller;

import com.project.dcb.dto.request.RegisterRequest;
import com.project.dcb.dto.response.TokenResponse;
import com.project.dcb.security.jwt.JwtTokenProvider;
import com.project.dcb.service.UserService;
import com.project.dcb.dto.request.LoginRequest;
import com.project.dcb.dto.response.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register") //user 가입
    public void register(@RequestBody RegisterRequest request) {
        userService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(HttpServletResponse response,@RequestBody LoginRequest request){
        System.out.println(request.getUsername()+" "+request.getPassword());
        TokenResponse tokenResponse = userService.login(request);
        response.addCookie(new Cookie("accessToken",tokenResponse.getAccessToken()));
        return new ResponseEntity<>(tokenResponse,new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/logout")
    public void logout(HttpServletResponse response){
        Cookie cookie = new Cookie("accessToken", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    @PatchMapping ("/user") //user 정보 수정
    public void modify(@RequestBody RegisterRequest request) {userService.register(request);}

    @GetMapping("/user") //user 정보조회
    public ResponseEntity<UserInfoResponse> getUserInfo(HttpServletRequest request) {
        return new ResponseEntity<>(userService.getUserInfo(),new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/adminRegister") //admin 권한부여
    public void adminRegister() {
        userService.adminRegister();
    }

    @GetMapping("/admin/manager/{username}/{authority}") //admin 권한부여
    public void authorization(@PathVariable String username,@PathVariable String authority) {
        userService.authorization(username, authority);
    }

}