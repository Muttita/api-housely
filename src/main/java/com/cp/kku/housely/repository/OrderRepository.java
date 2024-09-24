package com.cp.kku.housely.repository;

import com.cp.kku.housely.model.CustomerOrder;
import com.cp.kku.housely.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<CustomerOrder, Long> {
    @Query("SELECT co FROM CustomerOrder co WHERE co.user.id = :customerId")
    List<CustomerOrder> findCustomerOrdersByCustomerId(@Param("customerId") Long customerId);

    @Query("SELECT co FROM CustomerOrder co WHERE co.user.id = :customerId AND co.orderId = :orderId")
    Optional<CustomerOrder> findCustomerOrderByCustomerAndOrderId(@Param("customerId") Long customerId, @Param("orderId") Long orderId);


}

