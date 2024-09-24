package com.cp.kku.housely.repository;

import com.cp.kku.housely.model.OrderItem;
import com.cp.kku.housely.model.OrderItemKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemKey> {
    @Query("SELECT oi FROM OrderItem oi " +
            "JOIN oi.customerOrder co " +
            "WHERE co.orderId = :orderId")
    List<OrderItem> findOrderItemsByOrderId(@Param("orderId") Long orderId);


}
