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
    public String showAllProducts(Model model,@RequestParam(required = false, name = "minPrice") Float minPrice,
                                  @RequestParam(required = false, name = "maxPrice") Float maxPrice,
                                  @RequestParam(required = false, name = "word") String word
                                  ) {

        Page<Product> page = productService.findAll(null,null,null);
        model.addAttribute("products", page.getContent());
        model.addAttribute("productsCount", page.getTotalElements());
        model.addAttribute("currentPage",1);
        model.addAttribute("totalPage",page.getTotalPages());
        return "catalogProducts";
    }

    // http://localhost:8189/app/products/filter_products
    @GetMapping(path="/filter_products")
    public String filterProduct(Model model,
                                     @RequestParam(required = false, name = "minPrice") Float minPrice,
                                     @RequestParam(required = false, name = "maxPrice") Float maxPrice,
                                     @RequestParam(required = false, name = "word") String word,
                                     @RequestParam(required = false, name = "currentPage") Integer currentPage
                                     )
    {

        Page<Product> page=productService.findAll(minPrice,maxPrice,word);
        model.addAttribute("products", page.getContent());
        model.addAttribute("productsCount", page.getTotalElements());
        model.addAttribute("currentPage",1);
        model.addAttribute("totalPage",page.getTotalPages());
        model.addAttribute("minPrice",minPrice);
        model.addAttribute("maxPrice",maxPrice);
        model.addAttribute("word",word);

        return "catalogProducts";
    }

    // http://localhost:8189/app/products/filter_products
    @PostMapping(path="/next_page")
    public String nextPage(Model model,
                           @RequestParam(required = false, name = "minPrice") Float minPrice,
                           @RequestParam(required = false, name = "maxPrice") Float maxPrice,
                           @RequestParam(required = false, name = "word") String word,
                           @RequestParam(required = false, name = "currentPage") Integer currentPage,
                           @RequestParam(required = false, name = "totalPage") Integer totalPages)
    {
        if (currentPage!=totalPages){
            currentPage++;
        }
        Page<Product> page=productService.findAll(minPrice,maxPrice,word,currentPage);
        model.addAttribute("products", page.getContent());
        model.addAttribute("productsCount", page.getTotalElements());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage",totalPages);
        return "catalogProducts";
    }
    // http://localhost:8189/app/products/filter_products
    @PostMapping(path="/prev_page")
    public String prevPage(Model model,
                           @RequestParam(required = false, name = "minPrice") Float minPrice,
                           @RequestParam(required = false, name = "maxPrice") Float maxPrice,
                           @RequestParam(required = false, name = "word") String word,
                           @RequestParam(required = false, name = "currentPage") Integer currentPage)
    {
        if (currentPage>1){
            currentPage--;
        }
        Page<Product> page=productService.findAll(minPrice,maxPrice,word,currentPage);
        model.addAttribute("products", page.getContent());
        model.addAttribute("productsCount", page.getTotalElements());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage",page.getTotalPages());
        return "catalogProducts";
    }

}
