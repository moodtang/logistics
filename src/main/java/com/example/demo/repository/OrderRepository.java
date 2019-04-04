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
    ListOrderEntity findByOrderIdAndStatus(String OrderId,Integer status);

    @Query("select o from ListOrderEntity  o  where o.orderFromUser = ?1 or o.orderToUser = ?2 order by o.orderDate")
    List<ListOrderEntity> findAllByOrderAllUser(String orderFromUser,String orderToUser);


    @Query("select o from ListOrderEntity  o  where (o.orderFromUser = ?1 or o.orderToUser = ?2)and o.status < ?3 order by o.orderDate")
    List<ListOrderEntity> findAllByOrderAllUserAndStatusLessThan(String orderFromUser,String orderToUser,Integer status);


    @Query("select o from ListOrderEntity  o  where o.orderFromUser = ?1 and o.status = ?2 order by o.orderDate")
    List<ListOrderEntity> findAllByOrderFromUserAndByStatus(String orderFromUser,Integer status);


    @Query("select o from ListOrderEntity  o  where o.orderFromUser = ?1 order by o.orderDate")
    List<ListOrderEntity> findAllByOrderFromUser(String orderFromUser);

    @Query("select o from ListOrderEntity  o where o.orderToUser = ?1 order by o.orderDate")
    List<ListOrderEntity> findAllByOrderToUser(String orderToUser);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update  ListOrderEntity o set o.status = 1 ,o.orderToUser = ?1 ,o.levelToUser = ?2 where o.orderId = ?3")
    Integer acceptOrder(String orderToUser,Integer levelToUser,String orderId);
    void save(ListOrderEntity listOrderEntity);
    @Modifying(clearAutomatically = true)
    @Query("update  ListOrderEntity o set o.status = 2 where o.orderId = ?1")
    Integer completeByOrderToUser(String orderId);
    @Modifying(clearAutomatically = true)
    @Query("update  ListOrderEntity o set o.status = 3 where o.orderId = ?1")
    Integer completeByOrderFromUser(String orderId);
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
    List<ListOrderEntity> findAllByOrderToUserAndStatus(String orderFromUser,Integer status);
    @Modifying(clearAutomatically = true)
    @Query("select o from ListOrderEntity o order by o.orderDate")
    List<ListOrderEntity> getAllList();
    @Modifying(clearAutomatically = true)
    @Transactional
    Integer deleteByOrderId(String orderId);


}
