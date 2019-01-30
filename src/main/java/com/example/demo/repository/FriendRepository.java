package com.example.demo.repository;

import com.example.demo.entity.FriendsEntity;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by computer on 2019/1/30.
 */
public interface FriendRepository extends Repository<FriendsEntity,Integer> {
    void save(FriendsEntity friendsEntity);
    List<FriendsEntity> findAllByName(String name);
    @Transactional
    void deleteFriendsEntityByNameAndAndUserId(String name,String userId);
}
