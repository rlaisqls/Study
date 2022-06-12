package com.study.jpastudy;

import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter
public class User {
    @Id
    @GeneratedValue
    Long id;

    String username;
    String password;
}