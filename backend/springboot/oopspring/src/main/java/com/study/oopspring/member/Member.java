package com.study.oopspring.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Member {

    private Long id;
    private String name;
    private Grade grade;

    public Long getId() {
        return id;
    }
}