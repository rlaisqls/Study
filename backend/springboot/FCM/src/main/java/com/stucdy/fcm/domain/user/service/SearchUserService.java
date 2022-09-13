package com.stucdy.fcm.domain.user.service;

import com.stucdy.fcm.domain.user.domain.repository.UserRepository;
import com.stucdy.fcm.domain.user.presentation.dto.response.SearchUserListResponse;
import com.stucdy.fcm.domain.user.presentation.dto.response.SearchUserResponse;
import com.stucdy.fcm.global.util.RegexpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SearchUserService {

    private final UserRepository userRepository;

    public SearchUserListResponse execute(String searchQuery) {

        if (searchQuery.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")) {

            String searchRegexp = RegexpUtil.koreanSearchRegexp(searchQuery);

            List<SearchUserResponse> searchUserResponses =
                    userRepository
                            .findTop10ByNameRegexp(searchRegexp)
                            .stream()
                            .map(SearchUserResponse::of)
                            .collect(Collectors.toList());

            return new SearchUserListResponse(searchUserResponses);
        } else {

            List<SearchUserResponse> searchUserResponses =
                    userRepository.findTop10ByEmailContains(searchQuery)
                            .stream()
                            .map(SearchUserResponse::of)
                            .collect(Collectors.toList());

            return new SearchUserListResponse(searchUserResponses);
        }
    }

}