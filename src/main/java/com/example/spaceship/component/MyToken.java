package com.example.spaceship.component;

import com.example.spaceship.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyToken {
    public static  final String AUTHORIZATION ="authorization";
    public static  final String UID="uid";
    public static  final String ROLE="role";
    private Integer uid;
    private User.Role role;
}
