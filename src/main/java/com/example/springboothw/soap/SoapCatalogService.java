package com.example.springboothw.soap;

import com.example.springboothw.services.ProductService;
import com.example.springboothw.soap.catalog.ProductsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SoapCatalogService {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService=productService;
    }

    public ProductsList getAllProductsList() {
        ProductsList productsList = new ProductsList();
        productsList.getProduct().addAll(productService.findAllBy());
        return productsList;
    }
}
