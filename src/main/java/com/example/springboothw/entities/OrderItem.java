package com.example.springboothw.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

   @Entity
    @Table(name = "orders_items")
    @NoArgsConstructor
    @Data
    public class OrderItem {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "orders_items_id")
        private Long id;

        @ManyToOne
        @JoinColumn(name = "product_id")
        private Product product;

        @Column(name = "quantity_fld")
        private int quantity;

        @Column(name = "cost_fld")
        private BigDecimal cost;

        @ManyToOne
        @JoinColumn(name = "order_id")
        private Order order;

        public OrderItem(Product product) {
            this.product = product;
            this.quantity = 1;
            this.cost = product.getCost();
        }
    }
