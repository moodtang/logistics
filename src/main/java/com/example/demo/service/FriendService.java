package com.example.demo.service;

import com.example.demo.entity.FriendsEntity;
import com.example.demo.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by computer on 2019/1/30.
 */
@Service
public class FriendService {
    @Autowired
    private FriendRepository friendRepository;
    /*
    * 添加好友
    * */
    @Transactional
    public void add(FriendsEntity friendsEntity){
        friendRepository.save(friendsEntity);
    }
//    h获取好友列表
    public List<FriendsEntity> getList(String name){
        return friendRepository.findAllByName(name);
    }
    /*
    * 删除好友
    * */
    public String deleteFriend(String name,String userId){
        friendRepository.deleteFriendsEntityByNameAndAndUserId(name,userId);
        return "delete friend";
    }
}
