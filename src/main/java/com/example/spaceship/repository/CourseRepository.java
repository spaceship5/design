package com.example.spaceship.repository;

import com.example.spaceship.entity.Course;
import com.example.spaceship.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface CourseRepository extends BaseRepository<Course,Integer>{
    @Query("from Course  c where  c.teacher.id=:Tid")
    List<Course> findByTid(@Param("Tid") Integer Tid);

}
