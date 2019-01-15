package com.example.demo.service;

import com.example.demo.entity.ListOrderEntity;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    /*
     * 获取未接单列表
     * */
    @Transactional
    public List<ListOrderEntity> getOrderListNot(){
        return orderRepository.findAllByStatus(0);
    }
    /*
     * 获取已接单列表
     * */
    @Transactional
    public List<ListOrderEntity> getOrderList(){
        return orderRepository.findAllByStatus(1);
    }
    /*
    * 根据订单号查询订单
    * */
    @Transactional
    public ListOrderEntity getOrderById(String orderId){
        return orderRepository.findByOrderId(orderId);
    }
    /*
    * 根据下单者查询订单
    * */
    @Transactional
    public List<ListOrderEntity> getOrderByFromUser(String username){
        return orderRepository.findAllByOrderFromUser(username);
    }
    /*
    * 接单
    * */
    @Transactional
    public String acceptOrder(ListOrderEntity listOrderEntity){
        if (orderRepository.acceptOrder(listOrderEntity.getOrderToUser(),listOrderEntity.getLevelToUser(),listOrderEntity
        .getOrderId()) >0){
            return "accept order success";
        }
        else
            return "accept order fail";
    }

}
