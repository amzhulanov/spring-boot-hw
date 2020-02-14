package com.example.springboothw.repositories;

import com.example.springboothw.entities.Product;

import com.example.springboothw.soap.catalog.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product>{
    //List<ProductDto> findAllBy();
    List<Product> findAllBy();
}
