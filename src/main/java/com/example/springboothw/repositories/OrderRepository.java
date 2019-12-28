package com.example.springboothw.repositories;

import com.example.springboothw.entities.Order;
import com.example.springboothw.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("from Order o where o.user=:user")
    List<Order> findAllOrdersByUser(User user);
}
