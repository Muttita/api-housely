package com.cp.kku.housely.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cp.kku.housely.model.CustomerOrder;
import com.cp.kku.housely.repository.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<CustomerOrder> findAll() {
        return orderRepository.findAll();
    }

    public CustomerOrder save(CustomerOrder order) {
        return orderRepository.save(order);
    }

    public CustomerOrder findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}
