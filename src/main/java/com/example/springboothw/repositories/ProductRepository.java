package com.example.springboothw.repositories;

import com.example.springboothw.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product>{
    @Query("SELECT DISTINCT category FROM Product")
    List<String> findAllCategories();
}
