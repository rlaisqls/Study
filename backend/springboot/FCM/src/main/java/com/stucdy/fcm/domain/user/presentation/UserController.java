package com.stucdy.fcm.domain.user.presentation;

import com.stucdy.fcm.domain.auth.presentation.dto.TokenResponse;
import com.stucdy.fcm.domain.user.presentation.dto.request.SignInRequest;
import com.stucdy.fcm.domain.user.presentation.dto.request.SignUpRequest;
import com.stucdy.fcm.domain.user.presentation.dto.response.SearchUserListResponse;
import com.stucdy.fcm.domain.user.service.SearchUserService;
import com.stucdy.fcm.domain.user.service.SignInService;
import com.stucdy.fcm.domain.user.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final SignUpService signUpService;
    private final SignInService signInService;
    private final SearchUserService searchUserService;

    @PostMapping("")
    public TokenResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return signUpService.execute(request);
    }

    @PostMapping("/auth")
    public TokenResponse signIn(@RequestBody @Valid SignInRequest request) {
        return signInService.execute(request);
    }

    @GetMapping("/search")
    public SearchUserListResponse searchUser(@RequestParam String searchQuery) {
        return searchUserService.execute(searchQuery);
    }

}