package com.example.springboothw.services;

import com.example.springboothw.entities.Order;
import com.example.springboothw.entities.User;
import com.example.springboothw.utils.Cart;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrderService {
    Order save(Order order);
    Order save(Principal principal, Map<String, String> params, Cart cart);
    List<Order> findAllOrdersByUser(User user);
    BigDecimal costOrders(List<Order> orders);

    Optional<Order> findById(Long id);

    Integer checkOrders(User user);

    //List<OrderItem> findAllOrderItemsByOrders(List<Order> orders);
    //OrderItem findAllItemByOrder (Order order);
}
