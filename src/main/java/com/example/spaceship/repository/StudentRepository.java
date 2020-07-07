package com.example.spaceship.repository;

import com.example.spaceship.entity.Student;
import com.example.spaceship.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends BaseRepository<Student,Integer>{
}
