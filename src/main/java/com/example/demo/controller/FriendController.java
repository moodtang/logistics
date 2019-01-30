package com.example.demo.controller;

import com.example.demo.entity.FriendsEntity;
import com.example.demo.service.FriendService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;


/**
 * Created by computer on 2019/1/30.
 */
@RestController
@RequestMapping(value = "friend")
public class FriendController {
    @Autowired
    private FriendService friendService;
    /*
    * 添加好友*/
    @ApiOperation(value = "填写朋友列表信息",notes = "")
    @PostMapping(value = "add")
    public String add(FriendsEntity friendsEntity){
        for (Field f : friendsEntity.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            try {
                if (f.get(friendsEntity) == null) { //判断字段是否为空，并且对象属性中的基本都会转为对象类型来判断
                    return "数据不完整";
                    //这里可以给空字段初始化，及其他操作
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        friendService.add(friendsEntity);
        return "add friend success";
    }
    /*
    * 获取好友列表
    * */
    @ApiOperation(value = "根据用户名获取好友列表",notes = "String：name")
    @GetMapping(value = "getList")
    public HashMap getList(String name) {
        HashMap result = new HashMap<String,Object>();
        result.put("friendsList",friendService.getList(name));
        return result;
    }
    /*
    * 删除好友delete请求
    * */
    @ApiOperation(value = "删除好友",notes = "用户名String：name,被删除人String userId")
    @DeleteMapping(value = "delete")
    public HashMap delete(@RequestParam(value="name",required=false)String name,@RequestParam(value="userId",required=false) String userId) {
        HashMap result = new HashMap<String,Object>();
        result.put("msg",friendService.deleteFriend(name,userId));
        return result;
    }
    /*
    * 删除好友post请求
    * */
    @ApiOperation(value = "删除好友",notes = "用户名String：name,被删除人String userId")
    @PostMapping(value = "deleteFriend")
    public HashMap deleteFriend(String name, String userId) {
        HashMap result = new HashMap<String,Object>();
        result.put("msg",friendService.deleteFriend(name,userId));
        return result;
    }
}
