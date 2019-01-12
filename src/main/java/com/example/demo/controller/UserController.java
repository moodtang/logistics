package com.example.demo.controller;

import com.example.demo.annotation.UserLoginToken;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.TokenService;
import com.example.demo.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by computer on 2019/1/8.
 */

@RestController
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;
    /*
    * 测试
    * */
    @ApiOperation(value = "")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String HelloWorld() {
        return "Hello World!";
    }
    /*
    *  注册
    * */
    @ApiOperation(value = "注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public HashMap addUser(UserEntity userEntity) {
        HashMap result = new HashMap();
        result.put("uid",userService.addUser(userEntity));
        return  result;
    }
    //登录
    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public HashMap login(UserEntity user) {
        UserEntity userForBase=userService.findByUsername(user);
        HashMap tokenUser = new HashMap();
        if(userForBase==null){
            tokenUser.put("message", "登录失败,用户不存在");
            return tokenUser;
        }else {
            if (!userForBase.getPassword().equals(user.getPassword())){
                tokenUser.put("message", "登录失败,密码错误");
                return tokenUser;
            }else {
                String token = tokenService.getToken(userForBase);
                tokenUser.put("token", token);
                tokenUser.put("user", userForBase);
                return tokenUser;
            }
        }
    }
    /*
     *删除用户
     * */
//    @UserLoginToken
    @ApiOperation(value = "")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public HashMap deleteUser(@RequestParam("username")String username) {
        HashMap result = new HashMap();
        result.put("msg",userService.deleteUser(username));
        return result;
    }
    /*
     *获取用户列表
     * */
//    @UserLoginToken
    @ApiOperation(value = "用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public HashMap list() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        List<UserEntity> list = new ArrayList<>();
        list = userService.getUserList();
        result.put("userList",list);
        return result;
    }

    /*
    * 带token值测试
    * */
    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }
}
