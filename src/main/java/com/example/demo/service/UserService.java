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
    /*注册用户*/
    public String addUser(UserEntity userEntity){
        //      设置uid
        GetUid getUid =new GetUid();
        String uid = getUid.getUid();
        userEntity.setUserId(uid);
        if(userRepository.findUserEntityByUsername(userEntity.getUsername())!=null){
            return "the username have exited ";
        }
        if(userRepository.findUserEntityByUserPhone(userEntity.getUserPhone())!=null){
            return "the phone have exited ";
        }
        else
            userRepository.save(userEntity);
            return uid;
    }
    /*登录*/
    public UserEntity login(String username){
       return userRepository.findUserEntityByUsername(username);

    }
    public UserEntity findByUsername(UserEntity userEntity){
       return userRepository.findUserEntityByUsername(userEntity.getUsername());
    }
}
