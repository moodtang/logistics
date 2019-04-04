package com.example.demo.service;

import com.example.demo.assist.GetUid;
import com.example.demo.entity.BlackEntity;
import com.example.demo.entity.ListOrderEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.BlackRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class
OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private BlackRepository blackRepository;
    @Autowired
    private UserRepository userRepository;
   private List<BlackEntity> blackEntities;
    private List<String> beilahei_username;
    private  List<ListOrderEntity> listOrderEntities;
    /*
     * 获取未接单列表
     * */

    @Transactional
    public List<ListOrderEntity> getOrderListNot(String username) {
      beilahei_username = new ArrayList<>();
        //传入拉黑者即当前登陆用户的用户名，得到带有被拉黑人的id,再通过获取用户信息转换成用户名
        blackEntities= blackRepository.findAllByUsername(username);

        for (int k=0;k<blackEntities.size();k++){
            beilahei_username.add(userRepository.getUserEntitiesByUserId(blackEntities.get(k).getUserId()).getUsername());
        }


         listOrderEntities=orderRepository.findAllByStatus(0);
        for (int i =0;i<listOrderEntities.size();i++) {
            for (int j = 0; j < beilahei_username.size(); j++) {
                if ((listOrderEntities.get(i).getOrderFromUser()).equals(beilahei_username.get(j))){
                     listOrderEntities.remove(listOrderEntities.get(i));
                     break;
                }

            }
        }
        return listOrderEntities;
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
    public ListOrderEntity getOrderById(String orderId) {
        return orderRepository.findByOrderId(orderId);
    }

    /*
     * 根据订单号和状态为0查询订单
     * */
    @Transactional
    public ListOrderEntity getOrderByIdAndStatus(String orderId,Integer status) {
        return orderRepository.findByOrderIdAndStatus(orderId,status);
    }


    /*
     * 根据下单者和状态为0查询订单
     * */
    @Transactional
    public List<ListOrderEntity> getOrderByFromUserAndStatus(String username,Integer status) {
        return orderRepository.findAllByOrderFromUserAndByStatus(username,status);
    }

    /*
     * 根据下单者查询订单
     * */
    @Transactional
    public List<ListOrderEntity> getOrderByFromUser(String username) {
        return orderRepository.findAllByOrderFromUser(username);
    }

    /*
     * 根据接单者查询订单
     * */
    @Transactional
    public List<ListOrderEntity> getOrderByToUser(String username) {
        return orderRepository.findAllByOrderToUser(username);
    }
    /*
     * 查询该用户全部订单
     * */
    @Transactional
    public List<ListOrderEntity> getOrderByAllUser(String orderFromUser,String orderToUser) {
        return orderRepository.findAllByOrderAllUser(orderFromUser,orderToUser);
    }

    /*
     * 查询用户当前未完成订单
     * */
    @Transactional
    public List<ListOrderEntity> getOrderByAllUserAndStatus(String orderFromUser,String orderToUser,Integer status) {
        return orderRepository.findAllByOrderAllUserAndStatusLessThan(orderFromUser,orderToUser,status);
    }

    /*
     * 根据下单者和状态查询订单
     * */
    @Transactional
    public List<ListOrderEntity> getOrderByFromUserStatus(String username, Integer status) {
        return orderRepository.findAllByOrderFromUserAndStatus(username, status);
    }


    /*
     * 根据接单者和状态查询订单
     * */
    @Transactional
    public List<ListOrderEntity> getOrderByToUserStatus(String username, Integer status) {
        return orderRepository.findAllByOrderToUserAndStatus(username, status);
    }


    /*
     * 接单
     * */
    @Transactional
    public String acceptOrder(ListOrderEntity listOrderEntity) {
        if (orderRepository.acceptOrder(listOrderEntity.getOrderToUser(), listOrderEntity.getLevelToUser(), listOrderEntity
                .getOrderId()) > 0) {
            return "accept order success";
        } else
            return "accept order fail";
    }

    /*
     * 下单
     * */
    @Transactional
    public String addOrder(ListOrderEntity listOrderEntity) {
//       生成订单id
        GetUid getUid = new GetUid();
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
     * 接单方收货
     * */
    @Transactional
    public String completeByOrderToUser(String orderId) {

        if (orderRepository.completeByOrderToUser(orderId) > 0) {
            return "orderToUser complete success";
        } else {
            return "orderToUser complete fail";
        }
    }

    /*
     * 发单方收货
     * */
    @Transactional
    public String completeByOrderFromUser(String orderId) {

        if (orderRepository.completeByOrderFromUser(orderId) > 0) {
            return "orderFromUser complete success";
        } else {
            return "orderFromUser complete fail";
        }
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
    public List<ListOrderEntity> getAllOrderList(){
        return  orderRepository.getAllList();
    }
//    删除订单
    public String deleteOrder(String id){
        if (orderRepository.deleteByOrderId(id)>0){
            return "删除订单成功";
        }
        return "删除订单失败";
    }

}
