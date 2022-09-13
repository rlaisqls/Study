package com.stucdy.fcm.domain.user.presentation.dto.response;

import com.stucdy.fcm.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchUserResponse {

    private Long userId;
    private String userName;
    private String email;
    private String userProfileImage;

    public static SearchUserResponse of(User user) {
        return SearchUserResponse
                .builder()
                .userId(user.getId())
                .userName(user.getName())
                .email(user.getEmail())
                .userProfileImage(user.getProfileImage())
                .build();
    }

}