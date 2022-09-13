package com.stucdy.fcm.domain.user.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SearchUserListResponse {

    private List<SearchUserResponse> userList;
}