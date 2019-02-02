package com.example.demo.service;

import com.example.demo.assist.GetUid;
import com.example.demo.entity.InfoGoodsEntity;
import com.example.demo.entity.ListOrderEntity;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
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
   * 根据下单者和状态查询订单
   * */
    @Transactional
    public List<ListOrderEntity> getOrderByFromUserStatus(String username,Integer status){
        return orderRepository.findAllByOrderFromUserAndStatus(username,status);
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
    /*
    * 下单
    * */
    @Transactional
    public String addOrder(ListOrderEntity listOrderEntity){
//       生成订单id
        GetUid getUid =new GetUid();
        String orderUid = getUid.getUid();
        listOrderEntity.setOrderId(orderUid);
        //        生成货物id
//        String goodUid = getUid.getUid();
//        listOrderEntity.setGoodsId(goodUid);
//        生成时间
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String orderDate = formatter.format(currentTime);
        listOrderEntity.setOrderDate(orderDate);
//        设置订单默认状态
        listOrderEntity.setStatus(0);
        orderRepository.save(listOrderEntity);
        return orderUid;
    }
    /*
    * 收货
    * */
    @Transactional
    public String complete(String orderId){
        if (orderRepository.complete(orderId)>0){
            return "complete success";
        }
        else
            return "complete fail";
    }
    /*
    *投诉评价
    * */
    @Transactional
    public String complainRemark(String orderId,String msg,Integer status){
        if (status == null)
            return "标志位为空";
        if(status == 1){
            if (orderRepository.complainFromUser(orderId,msg)>0)
                return "complain from user success";
        }
        if(status == 2){
            if (orderRepository.complainToUser(orderId,msg)>0)
                return "complain to user success";
        }
        if(status == 3){
            if (orderRepository.remarkFromUser(orderId,msg)>0)
                return "remark from user success";
        }
        if(status == 4){
            if (orderRepository.remarkToUser(orderId,msg)>0)
                return "remark to user success";
        }
        return "remark or complain fail";
    }

}
