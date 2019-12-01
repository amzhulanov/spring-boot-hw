package com.example.springboothw.controllers;

import com.example.springboothw.entities.Product;
import com.example.springboothw.repositories.ProductSpecifications;
import com.example.springboothw.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/products")
    @ResponseBody
    public List<Product> getAllItems() {
        return productService.findAll();
    }

    @GetMapping("/filteringAndPaging")
    public String pagingExample(Model model,
                                @RequestParam(required = false, name = "min_cost") Float minPrice,
                                @RequestParam(required = false, name = "max_cost") Float maxPrice,
                                @RequestParam(required = false, name = "word") String word
    ) {
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
       // Page<Product> page = productRepository.findAll(spec, PageRequest.of(0, 5, Sort.Direction.ASC, "cost"));
        Page<Product> page= productService.findAll(spec, PageRequest.of(0, 5, Sort.Direction.ASC, "cost"));
        model.addAttribute("products", page.getContent());
        model.addAttribute("productsCount", page.getTotalElements());
        return "catalogProducts";
    }


}
