package com.example.demo.service;

import com.example.demo.assist.GetUid;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by computer on 2019/1/9.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    /*添加用户*/
    public String addUser(UserEntity userEntity){
        GetUid getUid =new GetUid();
//      设置uid
        String uid = getUid.getUid();
        userEntity.setUserId(uid);
        userRepository.save(userEntity);
        return uid;
    }
}
