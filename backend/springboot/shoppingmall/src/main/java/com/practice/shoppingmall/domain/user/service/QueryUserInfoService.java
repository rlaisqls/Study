package com.practice.shoppingmall.domain.user.service;

import com.practice.shoppingmall.domain.user.domain.User;
import com.practice.shoppingmall.domain.user.facade.UserFacade;
import com.practice.shoppingmall.domain.user.presentation.dto.response.QueryUserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueryUserInfoService {

    private final UserFacade userFacade;

    public QueryUserInfoResponse execute(Long userId){

        User user = userFacade.getUserById(userId);

        return QueryUserInfoResponse
                .builder()
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}