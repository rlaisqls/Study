package com.practice.shoppingmall.dto.response.user;

import com.practice.shoppingmall.dto.response.Response;
import com.practice.shoppingmall.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;


@Getter
@AllArgsConstructor
public class SignUpUserResponse implements Response {

    private final UUID uuid;

}