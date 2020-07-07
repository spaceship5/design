package com.example.spaceship.repository;

import com.example.spaceship.entity.Question;
import com.example.spaceship.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends BaseRepository<Question,Integer>{
}
