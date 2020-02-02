package com.example.springboothw.rest;

import com.example.springboothw.entities.Product;
import com.example.springboothw.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/products")
@RestController
public class ProductRestController {
    private ProductService productService;

    @Autowired
    public void setProductRestController(ProductService productService){
        this.productService=productService;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.findById(id);
    }

    @GetMapping
    public List<Product> getAllProduct(){

        return productService.findAll();
    }

    @PostMapping //add new product - Ok
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product){
        product.setId(new Long((int)(Math.random()*10000)));
        return productService.save(product);
    }

    @PutMapping //edit product
    @ResponseStatus(HttpStatus.OK)
    public Product updateProduct(@RequestBody Product product){
        return productService.update(product);
    }

    @DeleteMapping("/{id}")//delete one product - Ok
    public int deleteProduct(@PathVariable Long id){
        productService.deleteById(id);
        return HttpStatus.OK.value();
    }
}
