package com.example.springboothw.services.impl;

import com.example.springboothw.entities.Product;
import com.example.springboothw.entities.Review;
import com.example.springboothw.repositories.ProductRepository;
import com.example.springboothw.services.ProductService;
import com.example.springboothw.utils.spec.ProductSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    private void init(){

    }

    @Override
    public Page<Product> findAll(Float minPrice, Float maxPrice, String word) {

        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductSpecifications.priceGreaterThanOrEq(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpecifications.priceLesserThanOrEq(maxPrice));
        }
        if (word != null) {
            spec = spec.and(ProductSpecifications.titleContains(word));
        }
        Page<Product> page = productRepository.findAll(spec, PageRequest.of(0, 5, Sort.Direction.ASC, "cost"));

        return page;
    }

    @Override
    public Page<Product> findAll(Float minPrice, Float maxPrice, String word, Integer currentPage) {

        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductSpecifications.priceGreaterThanOrEq(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpecifications.priceLesserThanOrEq(maxPrice));
        }
        if (word != null) {
            spec = spec.and(ProductSpecifications.titleContains(word));
        }
        Page<Product> page = productRepository.findAll(spec, PageRequest.of(currentPage, 5, Sort.Direction.ASC, "cost"));

        return page;
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        Product editProduct=productRepository.findById(product.getId()).get();
        editProduct.setTitle(product.getTitle());
        editProduct.setCategory(product.getCategory());
        editProduct.setCost(product.getCost());
        editProduct.setDescription(product.getDescription());

        return productRepository.save(editProduct);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }



    @Override
    public Page<Product> findAll(Specification<Product> spec, Pageable pageable) {
        return productRepository.findAll(spec,pageable);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }


}
