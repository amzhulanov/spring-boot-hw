package com.example.springboothw.services;

import com.example.springboothw.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface ProductService extends JpaSpecificationExecutor<Product> {
    List<Product> findAll();

}
