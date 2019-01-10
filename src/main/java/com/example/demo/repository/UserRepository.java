package com.example.demo.repository;

import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by computer on 2019/1/9.
 */
public interface UserRepository extends JpaRepository<UserEntity,Integer>{
}
