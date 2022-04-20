package com.example.practice.service;

import com.example.practice.dto.CreateUserDto;
import com.example.practice.entity.User;
import com.example.practice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestService {

    @Autowired
    UserRepository userRepository;

    public void createUser(CreateUserDto userDto) {
        User user = new User(userDto);
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
