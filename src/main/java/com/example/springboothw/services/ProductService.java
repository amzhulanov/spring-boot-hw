package com.example.springboothw.services;

import com.example.springboothw.entities.Product;
import org.springframework.data.domain.Page;

public interface ProductService  {
    Page<Product> findAll(Float minPrice, Float maxPrice, String word);

}
