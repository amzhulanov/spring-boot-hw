package com.example.springboothw.controllers;

import com.example.springboothw.entities.Product;
import com.example.springboothw.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String index() {
        return "";
    }

    // http://localhost:8189/app/products/show
    @GetMapping(path = "/show")
    public String showAllProducts(Model model) {
        Page<Product> page = productService.findAll(null,null,null);
        model.addAttribute("products", page.getContent());
        return "catalogProducts";
    }

    // http://localhost:8189/app/products/filter_products
    @GetMapping(path="/filter_products")
    public String filterProduct(Model model,
                                     @RequestParam(required = false, name = "minPrice") Float minPrice,
                                     @RequestParam(required = false, name = "maxPrice") Float maxPrice,
                                     @RequestParam(required = false, name = "word") String word)
    {
        System.out.println("minPrice="+minPrice);
        System.out.println("maxPrice="+maxPrice);
        System.out.println("word="+word);
        Page<Product> page=productService.findAll(minPrice,maxPrice,word);
        model.addAttribute("products", page.getContent());
        model.addAttribute("productsCount", page.getTotalElements());
        return "catalogProducts";
    }
}
