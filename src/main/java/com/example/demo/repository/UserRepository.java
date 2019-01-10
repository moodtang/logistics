package com.example.demo.repository;

import com.example.demo.entity.UserEntity;
import org.springframework.data.repository.Repository;

/**
 * Created by computer on 2019/1/9.
 */
public interface UserRepository extends Repository<UserEntity,Integer> {
    UserEntity findUserEntityByUsername(String username);
    UserEntity findUserEntityByUserPhone(String userPhone);
    int save(UserEntity userEntity);
}
