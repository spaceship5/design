package com.example.spaceship.service;

import com.example.spaceship.entity.Student;
import com.example.spaceship.entity.Teacher;
import com.example.spaceship.entity.User;
import com.example.spaceship.repository.StudentRepository;
import com.example.spaceship.repository.TeacherRepository;
import com.example.spaceship.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;
    public User getUserByNum(Integer number) {
       return userRepository.find(number);
    }

    public Teacher addTeacher(Teacher tea, User user) {
        tea.setUser(user);
        teacherRepository.saveAndFlush(tea);
        teacherRepository.refresh(tea);
        return tea;
    }
    public Student addStudent(Student student, User user) {
        student.setUser(user);
        studentRepository.saveAndFlush(student);
        studentRepository.refresh(student);
        return student;
    }

    public List<Student> listStudent() {
        return studentRepository.findAll();
    }
    public List<Teacher> listTeacher() {
        return teacherRepository.findAll();
    }
    public User updateUser(Integer id,Integer number,String name,String password){
        User user = userRepository.findById(id).orElse(null);
        user.setName(name);
        user.setNumber(number);
        user.setPassword(password);
        return  user;
    }
    public Boolean deleteStudent(Integer id){
        studentRepository.deleteById(id);
        return true;
    }
    public Boolean deleteTeacher(Integer id){
        teacherRepository.deleteById(id);
        return  true;
    }

    public User getUserById(Integer id) {
        return  userRepository.findById(id).orElse(null);
    }
}
