package com.example.spaceship.repository;

import com.example.spaceship.entity.Course;
import com.example.spaceship.entity.Elective;
import com.example.spaceship.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ElectiveRepository extends BaseRepository<Elective,Integer>{
    @Query("from Elective  e where  e.student.id=:sid and e.course.id=:cid")
    Elective find(@Param("cid") Integer cid,@Param("sid") Integer sid);
    @Query("select e.course from Elective  e where  e.student.id=:sid")
    List<Course> find(@Param("sid") Integer sid);

}
