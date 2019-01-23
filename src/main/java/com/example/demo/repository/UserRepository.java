package com.example.demo.repository;

import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by computer on 2019/1/9.
 */
public interface UserRepository extends Repository<UserEntity,Integer> {
    UserEntity findUserEntityByUsername(String username);
    UserEntity findUserEntityByUserPhone(String userPhone);
     void  save(UserEntity userEntity);
     int deleteUserEntityByUsername(String username);
    @Query("select userId, username,userPhone from UserEntity ")
     List<UserEntity> getUserList();
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update UserEntity u set u.userHead = ?2 where u.userId = ?1")
    Integer saveHeadPic(String uid,String picUrl);

}
