package com.example.demo.service;

import com.example.demo.assist.GetUid;
import com.example.demo.entity.InfoGoodsEntity;
import com.example.demo.repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by computer on 2019/1/24.
 */
@Service
public class GoodService {
    @Autowired
    private GoodRepository goodRepository;
    /*
      *
      * 添加货物信息
      * */
    @Transactional
    public String addGoodsInfo(InfoGoodsEntity infoGoodsEntity) {
        //        生成货物id
        GetUid getUid =new GetUid();
        String goodUid = getUid.getUid();
        infoGoodsEntity.setGoodsId(goodUid);
        goodRepository.save(infoGoodsEntity);
        return goodUid;
    }
    /*
  *
  * 根据订单号获取货物信息
  * */
    @Transactional
    public List<InfoGoodsEntity> getGoodsInfoByOrderId(String orderId) {
      return   goodRepository.getAllByOrderId(orderId);
    }
        /*
    *
    * 根据货物id号获取货物信息
    * */
    @Transactional
    public InfoGoodsEntity getGoodsInfoByGoodId(String goodId) {
        return   goodRepository.getByGoodsId(goodId);
    }
}
