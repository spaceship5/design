package com.example.spaceship.repository;

import com.example.spaceship.entity.Teacher;
import com.example.spaceship.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends BaseRepository<Teacher,Integer>{
}
