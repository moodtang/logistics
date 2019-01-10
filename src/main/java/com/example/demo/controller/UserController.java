package com.example.demo.controller;

import com.example.demo.annotation.UserLoginToken;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.TokenService;
import com.example.demo.service.UserService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String HelloWorld() {
        return "Hello World!";
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(UserEntity userEntity) {
        return userService.addUser(userEntity);
    }
    //登录
    @PostMapping("/login")
    public HashMap login(UserEntity user) throws JSONException {
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
    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }
}
