package com.project.todolist.dto.response;

import com.project.todolist.entity.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FindUserInfoResponse {
    private String name;
    private int age;

    public static FindUserInfoResponse of(User user) {
        return FindUserInfoResponse
                .builder()
                .name(user.getName())
                .age(user.getAge())
                .build();
    }
}