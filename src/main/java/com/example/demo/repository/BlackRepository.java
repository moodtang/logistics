package com.example.demo.repository;

import com.example.demo.entity.BlackEntity;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.List;

public interface BlackRepository extends Repository<BlackEntity,Integer> {
    void save(BlackEntity blackEntity);
    List<BlackEntity> findAllByUsername(String username);
    @Transactional
    void deleteBlackEntityByUsernameAndUserId(String username,String userId);
}