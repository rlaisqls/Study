package com.example.practice.controller;

import com.example.practice.dto.CreateUserDto;
import com.example.practice.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @Autowired
    TestService testService;

    @PostMapping
    public void createUser(@RequestBody CreateUserDto userDto) {
        testService.createUser(userDto);
    }

    @DeleteMapping("/{user-id}")
    public void deleteUser(@PathVariable("user-id") Long parameter) {
        testService.deleteUser(parameter);
    }
}
