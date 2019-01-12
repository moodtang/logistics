package com.example.demo.service;

import com.example.demo.assist.GetUid;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by computer on 2019/1/9.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    /*注册用户*/
    @Transactional
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
        else{
            if(userEntity.getUsername() != null && userEntity.getPassword() != null && userEntity.getUserPhone() != null) {
                userRepository.save(userEntity);
                return uid;
            }
        }
            return "register fail";

    }
    /*登录*/
    public UserEntity login(String username){
       return userRepository.findUserEntityByUsername(username);

    }
    /*
    * 删除用户
    * */
    @Transactional
    public String deleteUser(String username){
        if (userRepository.deleteUserEntityByUsername(username)>0)
            return "删除用户成功";
        else
            return "删除用户失败";
    }
    /*通过用户名查找userEntity
    * */
    public UserEntity findByUsername(UserEntity userEntity){
       return userRepository.findUserEntityByUsername(userEntity.getUsername());
    }
    /*
    * 获取用户列表
    * */
    public List<UserEntity> getUserList(){
        return userRepository.getUserList();
    }
}
