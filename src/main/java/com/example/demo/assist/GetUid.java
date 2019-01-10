package com.example.demo.assist;

import java.util.UUID;

/**
 * Created by computer on 2019/1/9.
 */
public class GetUid {
    public String getUid(){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }

}
