package com.example.spaceship.repository;

import com.example.spaceship.entity.TestQuestion;
import com.example.spaceship.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface TestQuestionRepository extends BaseRepository<TestQuestion,Integer>{
}
