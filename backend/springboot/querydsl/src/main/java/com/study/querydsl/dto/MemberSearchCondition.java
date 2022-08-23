package com.study.querydsl.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSearchCondition {
    private String username;
    private String teamName;
    private Integer ageGoe;
    private Integer ageLoe;
}