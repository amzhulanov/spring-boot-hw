package com.example.springboothw.services;

import com.example.springboothw.entities.Product;
import com.example.springboothw.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void deleteByTitle(String word){
        productRepository.deleteByTitle(word);
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }


    @Override
    public Optional<Product> findOne(Specification<Product> specification) {
        return Optional.empty();
    }

    @Override
    public List<Product> findAll(Specification<Product> specification) {
        return null;
    }

    @Override
    public Page<Product> findAll(Specification<Product> specification, Pageable pageable) {
        return null;
    }

    @Override
    public List<Product> findAll(Specification<Product> specification, Sort sort) {
        return null;
    }

    @Override
    public long count(Specification<Product> specification) {
        return 0;
    }
}
