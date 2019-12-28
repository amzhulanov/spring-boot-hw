package com.example.springboothw.services;

import com.example.springboothw.entities.Order;
import com.example.springboothw.entities.User;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    Order save(Order order);
    List<Order> findAllOrdersByUser(User user);
    BigDecimal costOrders(List<Order> orders);
}
