package com.example.demo.controller;

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
    @ApiOperation(value = "未接单订单列表")
    @RequestMapping(value = "/notAcceptList", method = RequestMethod.GET)
    public HashMap NotAcceptOrder() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        List<ListOrderEntity> list = new ArrayList<>();
        list = orderService.getOrderListNot();
        result.put("userList",list);
        return result;
    }
    /*
     *获取已接单订单列表
     * */
//    @UserLoginToken
    @ApiOperation(value = "已接单订单列表")
    @RequestMapping(value = "/acceptList", method = RequestMethod.GET)
    public HashMap AcceptOrder() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        List<ListOrderEntity> list = new ArrayList<>();
        list = orderService.getOrderList();
        result.put("userList",list);
        return result;
    }
    /*
     *根据订单号查询订单
     * */
//    @UserLoginToken
    @ApiOperation(value = "根据订单号查询订单")
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    public HashMap getOrderById(String OrderId) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("Order",orderService.getOrderById(OrderId));
        return result;
    }
    /*
     *根据下单者查询订单
     * */
//    @UserLoginToken
    @ApiOperation(value = "根据下单者查询订单")
    @RequestMapping(value = "/findByFromUser", method = RequestMethod.POST)
    public HashMap getOrderByFromUser(String username) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("Order",orderService.getOrderByFromUser(username));
        return result;
    }
    /*
     *接单
     * */
//    @UserLoginToken
    @ApiOperation(value = "接单")
    @RequestMapping(value = "/acceptOrder", method = RequestMethod.POST)
    public HashMap acceptOrder(ListOrderEntity listOrderEntity) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("msg",orderService.acceptOrder(listOrderEntity));
        return result;
    }
    /*
    * 下单
    * */
    @ApiOperation(value = "下单",notes = "多个参数")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "", value = "", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "", value = "", required = true, dataType = "String")
//    })
    @PostMapping(value = "addOrder")
    public HashMap addOrder(ListOrderEntity listOrderEntity){
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("msg",orderService.addOrder(listOrderEntity));
        return result;
    }
}
