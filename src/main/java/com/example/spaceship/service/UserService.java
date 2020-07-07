package com.example.spaceship.service;

import com.example.spaceship.entity.Teacher;
import com.example.spaceship.entity.User;
import com.example.spaceship.repository.TeacherRepository;
import com.example.spaceship.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    public User getUserByNum(Integer number) {
       return userRepository.find(number);
    }

    public Teacher addTeacher(Teacher tea, User user) {
        tea.setUser(user);
        teacherRepository.saveAndFlush(tea);
        teacherRepository.refresh(tea);
        return tea;
    }
}
