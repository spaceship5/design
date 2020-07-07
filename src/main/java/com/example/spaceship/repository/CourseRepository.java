package com.example.spaceship.repository;

import com.example.spaceship.entity.Course;
import com.example.spaceship.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends BaseRepository<Course,Integer>{
}
