package com.example.demo.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.entity.UserEntity;
import org.springframework.stereotype.Service;

/**
 * Created by computer on 2019/1/10.
 */
@Service("TokenService")
public class TokenService {
    public String getToken(UserEntity user) {
        String token = "";
        token = JWT.create().withAudience(user.getUsername())// 将 user id 保存到 token 里面
                .sign(Algorithm.HMAC256(user.getPassword()));// 以 password 作为 token 的密钥
        return token;
    }
}