package com.example.demo.service;


import com.example.demo.entity.BlackEntity;
import com.example.demo.repository.BlackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class BlackService {
    @Autowired
    private BlackRepository blackRepository;

    /*
     * 添加黑名单
     * */
    @Transactional
    public void add(BlackEntity blackEntity){

        blackRepository.save(blackEntity);
    }

    //   获取黑名单列表
    public List<BlackEntity> getList(String username){
        return blackRepository.findAllByUsername(username);
    }
    /*
     * 删除黑名单
     * */
    public String deleteBlack(String username,String userId){
        blackRepository.deleteBlackEntityByUsernameAndUserId(username,userId);
        return "delete black";
    }

}
