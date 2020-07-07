package com.example.spaceship.service;

import com.example.spaceship.entity.User;
import com.example.spaceship.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User getUserByNum(Integer number) {
       return userRepository.find(number);
    }
}
