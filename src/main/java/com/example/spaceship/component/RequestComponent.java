package com.example.spaceship.component;

import com.example.spaceship.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

//用于获取用户Id，role
@Component
@Slf4j
public class RequestComponent {
    public int getUid(){
        log.debug("{}", RequestContextHolder.currentRequestAttributes().getAttribute(MyToken.AUTHORIZATION, RequestAttributes.SCOPE_REQUEST));
        return (int) RequestContextHolder.currentRequestAttributes().getAttribute(MyToken.UID, RequestAttributes.SCOPE_REQUEST);
    }
    public User.Role getRole(){
        return (User.Role) RequestContextHolder.currentRequestAttributes().getAttribute(MyToken.ROLE, RequestAttributes.SCOPE_REQUEST);
    }
}
