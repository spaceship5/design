package com.example.spaceship.repository;

import com.example.spaceship.entity.Test;
import com.example.spaceship.entity.TestQuestion;
import com.example.spaceship.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends BaseRepository<Test,Integer>{

	@Query("from TestQuestion t where t.test.id=:tid  ")
	List<TestQuestion> listTestQuestions(@Param("tid")Integer tid);

	@Query("from Course c  where c.id=:cid")
	public List<Test> listTests(@Param("cid") Integer cid );

	@Query("from Test t where t.course.id=:cid")
	Test findTest(@Param("cid")Integer cid);
}
