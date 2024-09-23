package com.cp.kku.housely.service;

import com.cp.kku.housely.model.OrderItem;
import com.cp.kku.housely.model.OrderItemKey;
import com.cp.kku.housely.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public OrderItem findById(OrderItemKey id) {
        return orderItemRepository.findById(id).orElseThrow(() -> new RuntimeException("OrderItem not found"));
    }

    public void deleteById(OrderItemKey id) {
        orderItemRepository.deleteById(id);
    }
}
