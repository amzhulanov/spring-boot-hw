package com.example.springboothw.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Column(name = "description_fld")
    private String description;

    @ManyToOne
    @JoinColumn(name = "product_id") //много отзывов соответствует одному товару
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id") //много отзывов соответствует одному пользователю
    private User user;
}
