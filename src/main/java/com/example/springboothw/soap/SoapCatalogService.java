package com.example.springboothw.soap;

import com.example.springboothw.services.ProductService;

import com.example.springboothw.soap.catalog.ProductDto;
import com.example.springboothw.soap.catalog.ProductsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SoapCatalogService {
    private ProductService productService;
    private List<ProductDto> productDtos;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService=productService;
    }

    public ProductsList getAllProductsList() {
        ProductsList productsList = new ProductsList();
        productDtos = new ArrayList<>();

        productDtos.addAll((Collection<? extends ProductDto>) productService.findAllBy());
        productsList.getProduct().addAll(productDtos);

        return productsList;
    }
}
