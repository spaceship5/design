package com.example.spaceship.repository;

import com.example.spaceship.entity.AnswerQuestion;
import com.example.spaceship.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerQuestionRepository extends BaseRepository<AnswerQuestion,Integer>{
    @Query("from AnswerQuestion  a where a.student.id=:sid and a.question.id=:qid")
    AnswerQuestion find(@Param("qid") Integer qid,@Param("sid") Integer sid);
}
