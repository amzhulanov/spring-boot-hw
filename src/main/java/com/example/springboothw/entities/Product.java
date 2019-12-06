package com.example.springboothw.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "category_fld")
    private String category;

    @Column(name = "description_fld")
    private String description;

    @Column(name = "title_fld")
    private String title;

    @Column(name = "cost_fld")
    private float cost;

}
