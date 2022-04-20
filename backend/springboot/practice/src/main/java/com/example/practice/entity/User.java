package com.example.practice.entity;

import com.example.practice.dto.CreateUserDto;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private int age;

    private String address;

    public User(CreateUserDto userDto) {
        name = userDto.getName();
        age = userDto.getAge();
        address = userDto.getAddress();
    }
}
