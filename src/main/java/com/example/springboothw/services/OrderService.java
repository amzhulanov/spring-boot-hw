package com.example.springboothw.services;

import com.example.springboothw.entities.Order;
import com.example.springboothw.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order save(Order order) {
        System.out.println("order save = "+order.toString());
        return orderRepository.save(order);
    }
}
