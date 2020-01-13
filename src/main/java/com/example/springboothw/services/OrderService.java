package com.example.springboothw.services;

import com.example.springboothw.entities.Order;
import com.example.springboothw.entities.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order save(Order order);
    List<Order> findAllOrdersByUser(User user);
    BigDecimal costOrders(List<Order> orders);

    Optional<Order> findById(Long id);

    //List<OrderItem> findAllOrderItemsByOrders(List<Order> orders);
    //OrderItem findAllItemByOrder (Order order);
}
