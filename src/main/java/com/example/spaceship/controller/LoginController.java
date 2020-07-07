package com.example.spaceship.controller;

import com.example.spaceship.component.EncryptComponent;
import com.example.spaceship.component.MyToken;
import com.example.spaceship.entity.Student;
import com.example.spaceship.entity.Teacher;
import com.example.spaceship.entity.User;
import com.example.spaceship.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
@Slf4j
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EncryptComponent encryptComponent;
    @Value("${my.teacher}")
    private  String roleTeacher;
    @Value("${my.student}")
    private  String roleStudent;
    @Value("${my.admin}")
    private  String roleAdmin;

    @PostMapping("login")
    public Map login(@RequestBody Map<String,String> login, HttpServletResponse response){
            User user= Optional.ofNullable(userService.getUserByNum(Integer.valueOf(login.get("username"))))
                    .filter(u->passwordEncoder.matches(login.get("password"),u.getPassword()))
                    .orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED,"用户名或密码错误"));
            MyToken myToken=new MyToken(user.getId(),user.getRole());
            String auth=encryptComponent.encryptToken(myToken);
            response.setHeader(MyToken.AUTHORIZATION, auth);
//        token中包含用户身份 但是前端不能通过解密token得到身份 因此通过编码用户身份传递给前端 编码后传递给前端增加安全性
            String roleCode=user.getRole()== User.Role.TEACHER?roleTeacher:(user.getRole()==User.Role.ADMIN?roleAdmin:roleStudent);
//        KeyPairGenerator.getInstance("RSA").generateKeyPair(); 获得密钥对
            return Map.of("role",roleCode);
    }

}
