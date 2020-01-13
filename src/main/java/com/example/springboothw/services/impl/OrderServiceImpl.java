package com.example.springboothw.services.impl;

import com.example.springboothw.entities.Order;
import com.example.springboothw.entities.User;
import com.example.springboothw.repositories.OrderRepository;
import com.example.springboothw.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order save(Order order) {

        return orderRepository.save(order);
    }

    public List<Order> findAllOrdersByUser(User user){
        return orderRepository.findAllOrdersByUser(user);
    }

    public BigDecimal costOrders(List<Order> orders){
        BigDecimal cost=new BigDecimal(0);
        for (Order order: orders) {
            cost=cost.add(order.getCost());
        }
        return cost;
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }
}
