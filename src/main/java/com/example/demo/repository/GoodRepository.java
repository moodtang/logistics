package com.example.demo.repository;

import com.example.demo.entity.InfoGoodsEntity;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by computer on 2019/1/24.
 */
public interface GoodRepository extends Repository<InfoGoodsEntity,Integer> {
    void save(InfoGoodsEntity infoGoodsEntity);
    List<InfoGoodsEntity> getAllByOrderId(String orderId);
    InfoGoodsEntity getByGoodsId(String goodId);
}
