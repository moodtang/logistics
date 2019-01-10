package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by computer on 2019/1/8.
 */

@RestController
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String HelloWorld() {
        return "Hello World!";
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(UserEntity userEntity) {
        return userService.addUser(userEntity);
    }
}
