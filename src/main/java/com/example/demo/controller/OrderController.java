package com.example.demo.controller;

import com.example.demo.entity.InfoGoodsEntity;
import com.example.demo.entity.ListOrderEntity;
import com.example.demo.service.OrderService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@RestController
@RequestMapping(value = "order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    /*
     *获取未接单订单列表
     * */
//    @UserLoginToken
    @ApiOperation(value = "未接单订单列表",notes = "无参数")
    @RequestMapping(value = "/notAcceptList", method = RequestMethod.GET)
    public HashMap NotAcceptOrder() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        List<ListOrderEntity> list = new ArrayList<>();
        list = orderService.getOrderListNot();
        result.put("orderList",list);
        return result;
    }
    /*
     *获取已接单订单列表
     * */
//    @UserLoginToken
    @ApiOperation(value = "已接单订单列表",notes = "无参数")
    @RequestMapping(value = "/acceptList", method = RequestMethod.GET)
    public HashMap AcceptOrder() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        List<ListOrderEntity> list = new ArrayList<>();
        list = orderService.getOrderList();
        result.put("orderList",list);
        return result;
    }
    /*
     *根据订单号查询订单
     * */
//    @UserLoginToken
    @ApiOperation(value = "根据订单号查询订单",notes = "orderId")
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    public HashMap getOrderById(String orderId) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("orderList",orderService.getOrderById(orderId));
        return result;
    }
    /*
     *根据下单者查询订单
     * */
//    @UserLoginToken
    @ApiOperation(value = "根据下单者查询订单",notes = "orderFromUser")
    @RequestMapping(value = "/findByFromUser", method = RequestMethod.POST)
    public HashMap getOrderByFromUser(String username) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("orderList",orderService.getOrderByFromUser(username));
        return result;
    }
    /*
     *接单
     * */
//    @UserLoginToken
    @ApiOperation(value = "接单",notes = "orderToUser,levelToUser,orderId")
    @RequestMapping(value = "/acceptOrder", method = RequestMethod.POST)
    public HashMap acceptOrder(ListOrderEntity listOrderEntity) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("msg",orderService.acceptOrder(listOrderEntity));
        return result;
    }
    /*
    * 下单
    * */
    @ApiOperation(value = "下单",notes = "订单id，时间自动生成")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "", value = "", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "", value = "", required = true, dataType = "String")
//    })
    @PostMapping(value = "addOrder")
    public HashMap addOrder(ListOrderEntity listOrderEntity){
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("orderId",orderService.addOrder(listOrderEntity));
        return result;
    }
    /*
     *收货
     * */
//    @UserLoginToken
    @ApiOperation(value = "收货",notes = "orderId")
    @RequestMapping(value = "/completeOrder", method = RequestMethod.POST)
    public HashMap completeOrder(String orderId) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("msg",orderService.complete(orderId));
        return result;
    }
    /*
    * 投诉&评价
    * */
    @ApiOperation(value = "投诉,评价",notes = "orderId,msg,标志位int型(1是下单者complain，2是接单者complain，3是下单者remark,4是接单者remark)")
    @RequestMapping(value = "/complainRemark", method = RequestMethod.POST)
    public HashMap complaintByFromUser(String orderId,String msg,Integer status) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("msg",orderService.complainRemark(orderId,msg,status));
        return result;
    }
    /*
    * 用户查看订单
    * */
    @ApiOperation(value = "根据下单者和订单状态查询订单",notes = "orderFromUser")
    @RequestMapping(value = "/getOrderByFromUserStatus", method = RequestMethod.POST)
    public HashMap getOrderByFromUserStatus(String username,Integer status) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("orderList",orderService.getOrderByFromUserStatus(username,status));
        return result;
    }

}
