package com.practice.shoppingmall.dto.response.user;

import com.practice.shoppingmall.dto.response.Response;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FindUserResponse implements Response {
    private String username;

    private String email;

    private String address;
}