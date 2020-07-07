package com.example.spaceship.repository;

import com.example.spaceship.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends BaseRepository<User,Integer>{
    @Query("from User u where u.number=:number")
    public User find(@Param("number") Integer number);
}
