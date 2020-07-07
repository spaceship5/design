package com.example.spaceship.repository;

import com.example.spaceship.entity.Resource;
import com.example.spaceship.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends BaseRepository<Resource,Integer>{
}
