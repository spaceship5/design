package com.example.spaceship.repository;

import com.example.spaceship.entity.AnswerTestQuestion;
import com.example.spaceship.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerTestQuestionRepository extends BaseRepository<AnswerTestQuestion,Integer>{
	@Query("select tq.answerTestQuestion from TestQuestion tq where tq.id=:tqid")
	List<AnswerTestQuestion> findAllATQ(@Param("")Integer tqid);
}
