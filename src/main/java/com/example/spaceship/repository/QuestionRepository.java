package com.example.spaceship.repository;

import com.example.spaceship.entity.Question;
import com.example.spaceship.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends BaseRepository<Question,Integer>{
    @Query("from Question  q where q.course.id=:cid")
    List<Question> list(@Param("cid") Integer cid);
}
