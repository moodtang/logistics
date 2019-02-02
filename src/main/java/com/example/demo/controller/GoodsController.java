package com.example.demo.controller;

import com.example.demo.entity.InfoGoodsEntity;
import com.example.demo.service.GoodService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by computer on 2019/1/24.
 */
@RestController
@RequestMapping(value = "good")
public class GoodsController {
    @Autowired
    private GoodService goodService;
    /*
    * 下单提交的货物信息
    * */
    @ApiOperation(value = "填写货物信息数组",notes = "货物id自动生成")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "", value = "", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "", value = "", required = true, dataType = "String")
//    })

    @PostMapping(value = "addGoods")
    public List addGoods(@RequestBody List<InfoGoodsEntity> infoGoodsEntitys){
        List<String> list = new ArrayList();
        infoGoodsEntitys.forEach(infoGoodsEntity -> {
            list.add(goodService.addGoodsInfo(infoGoodsEntity));
        });
        return list;
    }
    @ApiOperation(value = "填写货物信息",notes = "货物id自动生成")
    @PostMapping(value = "addGood")
    public Map addGood(InfoGoodsEntity infoGoodsEntity){
        Map map= new HashMap();
        map.put("goodId",goodService.addGoodsInfo(infoGoodsEntity));
        return map;
    }
    @ApiOperation(value = "根据订单号获取货物",notes = "返回数组形式")
    @GetMapping(value = "getGoodsInfoByOrderId")
    public Map getGoodsInfoByOrderId(String orderId){
        Map<String,Object> map=new HashMap();
        map.put("goodsList",goodService.getGoodsInfoByOrderId(orderId));
        return map;
    }
    @ApiOperation(value = "根据货物id获取货物",notes = "返回good")
    @GetMapping(value = "getGoodsInfoByGoodId")
    public Map getGoodsInfoByGoodId(String goodId){
        Map<String,Object> map=new HashMap();
        map.put("good",goodService.getGoodsInfoByGoodId(goodId));
        return map;
    }

}
