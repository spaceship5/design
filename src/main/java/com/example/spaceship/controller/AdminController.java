package com.example.spaceship.controller;

import com.example.spaceship.component.RequestComponent;
import com.example.spaceship.entity.Student;
import com.example.spaceship.entity.Teacher;
import com.example.spaceship.entity.User;
import com.example.spaceship.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/")
@Slf4j
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RequestComponent requestComponent;
    @GetMapping("students")
    public Map getStudents(){
        List<Student> students = userService.listStudent();
        return Map.of("students",students);
    }
    @GetMapping("teachers")
    public Map getTeachers(){
        List<Teacher> teachers = userService.listTeacher();
        return Map.of("teachers",teachers);
    }
    @GetMapping("user")
    public Map getUser(){
        User user = userService.getUserById(requestComponent.getUid());
        return Map.of("user",user);
    }
    @PatchMapping("user")
    public Map patchUser(@RequestBody Map<String,String> user){
        User user1 = userService.updateUser(Integer.valueOf(user.get("id")), Integer.valueOf(user.get("number")), user.get("name"), passwordEncoder.encode(user.get("password")));
        return Map.of("user",user1);
    }
    @DeleteMapping("student/{id}")
    public Map deleteStudent(@PathVariable Integer id){
        userService.deleteStudent(id);
        return Map.of("success",true);
    }
    @DeleteMapping("teacher/{id}")
    public Map deleteTeacher(@PathVariable Integer id){
        userService.deleteTeacher(id);
        return Map.of("success",true);
    }
    @PostMapping("teacher")
    public Map postTeacher(@RequestBody Map<String,String> teacher){
        User user=new User();
        user.setName(teacher.get("name"));
        user.setNumber(Integer.valueOf(teacher.get("number")));
        user.setPassword(passwordEncoder.encode(teacher.get("password")));
        Teacher teacher1=new Teacher();
        user.setRole(User.Role.TEACHER);
        userService.addTeacher(teacher1, user);
        return Map.of("teacher",teacher1);
    }
    @PostMapping("student")
    public Map postStudent(@RequestBody  Map<String,String> student){
        User user=new User();
        user.setName(student.get("name"));
        user.setNumber(Integer.valueOf(student.get("number")));
        user.setPassword(passwordEncoder.encode(student.get("password")));
        Student student1=new Student();
        user.setRole(User.Role.TEACHER);
        userService.addStudent(student1, user);
        return Map.of("student",student1);
    }

}
