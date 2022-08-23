package com.study.querydsl.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class MemberTeamDto {
    private final Long memberId;
    private final String username;
    private final Integer age;
    private final Long teamId;
    private final String teamName;

    @QueryProjection
    public MemberTeamDto(Long memberId, String username, Integer age, Long teamId, String teamName) {
        this.memberId = memberId;
        this.username = username;
        this.age = age;
        this.teamId = teamId;
        this.teamName = teamName;
    }
}