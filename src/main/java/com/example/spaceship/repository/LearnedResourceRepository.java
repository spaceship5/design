package com.example.spaceship.repository;

import com.example.spaceship.entity.LearnedResource;
import com.example.spaceship.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LearnedResourceRepository extends BaseRepository<LearnedResource,Integer>{
    @Query("from LearnedResource l where l.student.id=:sid and l.resource.id=:rid")
    LearnedResource find(@Param("sid") Integer sid,@Param("rid") Integer rid);
    @Query("select count(a) from LearnedResource  a where a.student.id=:sid and a.resource.course.id=:cid")
    Integer number(@Param("cid")Integer cid,@Param("sid") Integer sid);
}
