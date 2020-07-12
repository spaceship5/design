package com.example.spaceship.repository;

import com.example.spaceship.entity.Resource;
import com.example.spaceship.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ResourceRepository extends BaseRepository<Resource,Integer>{
    @Query("from Resource  r where r.course.id=:cid")
    List<Resource> list(@Param("cid")Integer cid);
}
