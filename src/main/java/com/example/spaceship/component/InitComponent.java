package com.example.spaceship.component;

import com.example.spaceship.entity.Teacher;
import com.example.spaceship.entity.User;
import com.example.spaceship.service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//实现初始化接口
@Component
public class InitComponent implements InitializingBean {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserService userService;
    @Override
    public void afterPropertiesSet() throws Exception {
        int num=1001;
        if(userService.getUserByNum(num)==null){
            User user=new User();
            user.setNumber(num);
            user.setName("root");
            user.setRole(User.Role.ADMIN);
            Teacher tea=new Teacher();
            user.setPassword(encoder.encode("root"));
            userService.addTeacher(tea, user);
        }
    }
}
