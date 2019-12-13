package com.example.springboothw.rest;

import com.example.springboothw.entities.Product;
import com.example.springboothw.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class ProductRestController {
    private ProductService productService;

    @Autowired
    public void setProductRestController(ProductService productService){
        this.productService=productService;
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.findById(id);
    }

    @GetMapping("/products")
    public List<Product> getAllProduct(){
        return productService.findAll();
    }

//    @PostMapping("/products")
//    public Product addProduct(@RequestBody Product product){
//        product.setId(0L);
//        return productService.add(product);
//    }

    @PutMapping("/products")
    public Product saveProduct(@RequestBody Product product){
        return productService.save(product);
    }

    @DeleteMapping("/products/{id}")
    public int deleteProduct(@PathVariable Long id){
        productService.deleteById(id);
        return HttpStatus.OK.value();
    }
}
