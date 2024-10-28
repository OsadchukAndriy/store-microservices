package com.store.repository;

import com.store.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order save(Order order);

    Order findOrderById(Long id);

    @Modifying
    @Query("UPDATE Order o SET o.status = :status WHERE o.id = :id")
    Order updateOrderStatus(@Param("id") Long id, @Param("status") Integer status);
}
