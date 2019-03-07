package com.example.demo.controller;


import com.example.demo.entity.BlackEntity;
import com.example.demo.entity.FriendsEntity;
import com.example.demo.service.BlackService;
import com.example.demo.service.FriendService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.HashMap;

@RestController
@RequestMapping(value = "black")
public class BlackController {
    @Autowired
    private BlackService blackService;


    /*
     * 添加黑名单*/
    @ApiOperation(value = "填写黑名单列表信息",notes = "")
    @PostMapping(value = "add")
    public String add(BlackEntity blackEntity){
        for (Field f : blackEntity.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            try {
                if (f.get(blackEntity) == null) { //判断字段是否为空，并且对象属性中的基本都会转为对象类型来判断
                    return "数据不完整";
                    //这里可以给空字段初始化，及其他操作
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        blackService.add(blackEntity);
        return "add black success";
    }

    /*
     * 获取黑名单列表
     * */
    @ApiOperation(value = "根据用户名获取黑名单列表",notes = "String：username")
    @GetMapping(value = "getList")
    public HashMap getList(String username) {
        HashMap result = new HashMap<String,Object>();
        result.put("blackList",blackService.getList(username));
        return result;
    }
    /*
     * 删除黑名单delete请求
     * */
    @ApiOperation(value = "删除黑名单",notes = "用户名String：username,被删除人String userId")
    @DeleteMapping(value = "delete")
    public HashMap delete(@RequestParam(value="username",required=false)String username,@RequestParam(value="userId",required=false) String userId) {
        HashMap result = new HashMap<String,Object>();
        result.put("msg",blackService.deleteBlack(username,userId));
        return result;
    }
    /*
     * 删除黑名单post请求
     * */
    @ApiOperation(value = "删除黑名单",notes = "用户名String：username,被删除人String userId")
    @PostMapping(value = "deleteBlack")
    public HashMap deleteBlack(String username, String userId) {
        HashMap result = new HashMap<String,Object>();
        result.put("msg",blackService.deleteBlack(username,userId));
        return result;
    }
}
