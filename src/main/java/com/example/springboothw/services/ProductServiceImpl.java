package com.example.springboothw.services;

import com.example.springboothw.entities.Product;
import com.example.springboothw.repositories.ProductRepository;
import com.example.springboothw.utils.spec.ProductSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
    public List<String> findAllCategories() {
        return productRepository.findAllCategories();
    }


    @Override
    public Page<Product> findAll(Specification<Product> spec, Pageable pageable) {
        return productRepository.findAll(spec,pageable);
    }



}
