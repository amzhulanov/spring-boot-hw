package com.example.springboothw.entities;

import com.example.springboothw.utils.Cart;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    @Column(name = "cost_fld")
    private BigDecimal cost;

    public Order(User user, Cart cart) {
        this.user = user;
        this.cost = cart.getCost();
        this.items = new ArrayList<>();
        for (OrderItem i : cart.getItems()) {
            i.setOrder(this);
            this.items.add(i);
        }
        cart.clear();
    }
}