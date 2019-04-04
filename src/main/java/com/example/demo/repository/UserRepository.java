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
    int deleteUserEntityByUserId(String userId);
    @Query("select userId, username,userPhone,creditScore,userLevel from UserEntity order by creditScore")
     List<UserEntity> getUserList();
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update UserEntity u set u.userHead = ?2 where u.userId = ?1")
    Integer saveHeadPic(String uid,String picUrl);
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update UserEntity u set u.userLevel = u.userLevel+1 where u.userId = ?1")
    Integer upgradeUser(String uid);
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update UserEntity u set u.userLevel = u.userLevel-1 where u.userId = ?1")
    Integer downgradeUser(String uid);
    @Transactional
    UserEntity getUserEntitiesByUserId(String userId);
    @Transactional
    UserEntity getUserEntitiesByUsername(String username);
    @Transactional
    @Query("select u.userId, u.username,u.userPhone,u.creditScore,u.userLevel from UserEntity u  where u.username = ?1")
    List getUserInfoByUsername(String username);
    //根据用户名修改密码
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update UserEntity u set u.password = ?2 where u.username = ?1")
    Integer changePasswordByUsername(String username,String password);
    @Query("select o.orderId,u.username,u.userHead,o.remarkFromUser,o.complaintFromUser from UserEntity u ,ListOrderEntity o where o.orderFromUser =u.username")
    List getComplain();
    @Query("select o.orderId,u.username,u.userHead,o.remarkToUser,o.complaintToUser from UserEntity u ,ListOrderEntity o where o.orderToUser =u.username")
    List getComplain1();
}
