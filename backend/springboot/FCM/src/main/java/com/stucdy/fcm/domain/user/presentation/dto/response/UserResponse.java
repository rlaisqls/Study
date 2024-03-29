package com.stucdy.fcm.domain.user.presentation.dto.response;


import com.stucdy.fcm.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {

    private Long userId;
    private String userName;
    private String profileImageUrl;

    public static UserResponse of(User user) {

        return UserResponse
                .builder()
                .userId(user.getId())
                .userName(user.getName())
                .profileImageUrl(user.getProfileImage())
                .build();
    }
}