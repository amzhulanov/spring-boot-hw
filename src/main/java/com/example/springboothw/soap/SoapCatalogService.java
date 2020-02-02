package com.example.springboothw.soap;

import com.example.springboothw.entities.Product;
import com.example.springboothw.services.ProductService;
//import com.example.springboothw.soap.catalog.ProductDto;
import com.example.springboothw.soap.catalog.ProductDto;
import com.example.springboothw.soap.catalog.ProductsList;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class SoapCatalogService {
    private List<ProductDto> productDtos;
    private ProductService productService;

    @PostConstruct
    public void init(ProductService productService) {
//        productDtos = new ArrayList<>();
//        ProductDto productDto1 = new ProductDto();
//        productDto1.setTitle("Product #1");
//        productDtos.add(productDto1);
//        ProductDto productDto2 = new ProductDto();
//        productDto2.setTitle("Product #2");
//        productDtos.add(productDto2);
        this.productService=productService;

    }

    public ProductsList getAllProductsList() {
        ProductsList productsList = new ProductsList();
        List<Product> products=productService.findAll();
        for (Product p:products) {
            ProductDto productDto=new ProductDto();
            productDto.setId(p.getId());
            productDto.setTitle(p.getTitle());
            productDto.setCategory(p.getCategory().getTitle());

            productDtos.add(productDto);
        }
        productsList.getProduct().addAll(productDtos);
        //productsList.getProduct().addAll(productDtos);
       // productsList.getProduct().addAll(productService.findAll());
        return productsList;
    }
}
