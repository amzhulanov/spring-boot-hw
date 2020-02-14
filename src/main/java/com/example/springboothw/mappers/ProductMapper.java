package com.example.springboothw.mappers;

import com.example.springboothw.entities.Product;
import com.example.springboothw.soap.catalog.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel="spring")
public interface ProductMapper {
    ProductMapper MAPPER= Mappers.getMapper(ProductMapper.class);

    ProductDto fromProduct(Product product);
    Product toProduct(ProductDto productDto);

    List<ProductDto> fromProductList(List<Product> products);
    List<Product> toProductList(List<ProductDto> productDtos);

}
