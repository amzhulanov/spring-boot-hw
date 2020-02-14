package com.example.springboothw.services;

import com.example.springboothw.soap.catalog.ProductDto;
import com.example.springboothw.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ProductService  {
    Page<Product> findAll(Float minPrice, Float maxPrice, String word);
    Page<Product> findAll(Float minPrice, Float maxPrice, String word,Integer currentPage);
    Page<Product> findAll(Specification<Product> spec, Pageable pageable);
    List<Product> findAll();
    List<ProductDto> findAllBy();
    Product findById(Long id);



    Product save(Product product);


    Product update(Product product);

    void deleteById(Long id);

}
