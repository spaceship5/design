package com.example.spaceship.repository;

import com.example.spaceship.entity.TestQuestion;
import com.example.spaceship.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TestQuestionRepository extends BaseRepository<TestQuestion,Integer>{
	@Query("from TestQuestion tq where tq.id=:tqid")
	TestQuestion findTestQuestion(@Param("tqid")Integer tqid);

}
