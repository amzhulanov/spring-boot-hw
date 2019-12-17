package com.example.springboothw.entities;

import javax.persistence.*;
import java.util.List;

@Entity //указывает, что данный бин (класс) является сущностью
@Table(name="categories") //имя таблицы, на которую сыслается сущность
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //указывает, что id будет генериться автоматически
    @Column(name="category_id")
    private Long id;

    @Column(name="title_fld")
    private String title;

    @OneToMany(mappedBy = "category") //одной категории соответствует много товаров
    private List<Product> products;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
