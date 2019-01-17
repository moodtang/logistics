package com.example.demo.repository;

import com.example.demo.entity.ListOrderEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderRepository extends Repository<ListOrderEntity,Integer> {
    List<ListOrderEntity> findAllByStatus(Integer status);
    ListOrderEntity findByOrderId(String OrderId);
    @Query("select o from ListOrderEntity  o where orderFromUser = ?1")
    List<ListOrderEntity> findAllByOrderFromUser(String orderFromUser);
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update  ListOrderEntity o set o.status = 1 ,o.orderToUser = ?1 ,o.levelToUser = ?2 where o.orderId = ?3")
    Integer acceptOrder(String orderToUser,Integer levelToUser,String orderId);
}
