package com.example.spaceship.repository;

import com.example.spaceship.entity.AnswerQuestion;
import com.example.spaceship.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerQuestionRepository extends BaseRepository<AnswerQuestion,Integer>{
    @Query("from AnswerQuestion  a where a.student.id=:sid and a.question.id=:qid")
    AnswerQuestion find(@Param("qid") Integer qid,@Param("sid") Integer sid);
    @Query("from AnswerQuestion a where a.question.id=:qid")
    List<AnswerQuestion> list(@Param("qid") Integer qid);
    @Query("select count(a) from AnswerQuestion  a where a.student.id=:sid and a.question.course.id=:cid")
    Integer number(@Param("cid")Integer cid,@Param("sid") Integer sid);
}
