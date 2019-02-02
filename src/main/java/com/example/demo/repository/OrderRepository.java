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
    @Query("select o from ListOrderEntity  o where o.orderFromUser = ?1")
    List<ListOrderEntity> findAllByOrderFromUser(String orderFromUser);
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update  ListOrderEntity o set o.status = 1 ,o.orderToUser = ?1 ,o.levelToUser = ?2 where o.orderId = ?3")
    Integer acceptOrder(String orderToUser,Integer levelToUser,String orderId);
    void save(ListOrderEntity listOrderEntity);
    @Modifying(clearAutomatically = true)
    @Query("update  ListOrderEntity o set o.status = 2 where o.orderId = ?1")
    Integer complete(String orderId);
    @Modifying(clearAutomatically = true)
    @Query("update  ListOrderEntity o set o.complaintFromUser = ?2 where o.orderId = ?1")
    Integer complainFromUser(String orderId,String msg);
    @Modifying(clearAutomatically = true)
    @Query("update  ListOrderEntity o set o.complaintToUser = ?2 where o.orderId = ?1")
    Integer complainToUser(String orderId,String msg);
    @Modifying(clearAutomatically = true)
    @Query("update  ListOrderEntity o set o.remarkFromUser = ?2 where o.orderId = ?1")
    Integer remarkFromUser(String orderId,String msg);
    @Modifying(clearAutomatically = true)
    @Query("update  ListOrderEntity o set o.remarkToUser = ?2 where o.orderId = ?1")
    Integer remarkToUser(String orderId,String msg);
    List<ListOrderEntity> findAllByOrderFromUserAndStatus(String orderFromUser,Integer status);


}
