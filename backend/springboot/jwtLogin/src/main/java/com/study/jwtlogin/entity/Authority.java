package com.study.jwtlogin.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
public enum Authority {
    ROLE_USER, ROLE_ADMIN
}