package com.example.springboothw.controllers;

import com.example.springboothw.entities.*;
import com.example.springboothw.utils.Cart;
import com.example.springboothw.services.CategoryService;
import com.example.springboothw.services.ProductService;
import com.example.springboothw.utils.ProductFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/products")
public class ProductController {
    private CategoryService categoryService;
    private ProductService productService;
    private Cart cart;

    @Autowired
    public ProductController(CategoryService categoryService,
                             ProductService productService,
                             Cart cart) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.cart = cart;
    }

    // http://localhost:8189/app/products/show
    @GetMapping(path = "/show")
    public String showAllProducts(Model model, @RequestParam Map<String, String> params) {
        int pageIndex = 0;
        if (params.containsKey("p")) {
            pageIndex = Integer.parseInt(params.get("p")) - 1;
        }
        Pageable pageRequest = PageRequest.of(pageIndex, 3);
        ProductFilter productFilter = new ProductFilter(params);
        Page<Product> page = productService.findAll(productFilter.getSpec(), pageRequest);
        List<Category> categories = categoryService.getAll();

        model.addAttribute("filtersDef", productFilter.getFilterDefinition());
        model.addAttribute("page", page);
        model.addAttribute("categories", categories);

        return "catalog_products";
    }

    // http://localhost:8189/app/products/edit/2
    @GetMapping(path = "/edit/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public String editProduct(Model model, @PathVariable Long id) {
        Product product = productService.findById(id);
        List<Category> categories = categoryService.getAll();
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "product_form";
    }

    // http://localhost:8189/app/products/edit
    @PostMapping("/edit")
    public String saveProduct(@ModelAttribute(name = "product") Product product) {
        productService.save(product);
        return "redirect:/products/show";
    }

    @GetMapping(path = "/cart/show")
    public String showCart(Model model) {
        model.addAttribute("cart", cart);
        return "cart";
    }

    @GetMapping(path = "/cart/add/{index}")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public String addProductToCart(@PathVariable Long index, HttpServletRequest request, HttpServletResponse response) throws IOException {
        cart.add(productService.findById(index));
        response.sendRedirect(request.getHeader("referer"));
        return "success";
    }

    @GetMapping(path = "/cart/countRequest")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public Integer getCartCount() {
        return cart.countProdCart();
    }

    @GetMapping("/cart/del/{id}")
    public String cartDelById(@PathVariable Long id) {
        System.out.println("id продукта на удаление = " + id);
        cart.removeById(id);
        return "redirect:/products/cart/show";
    }


}
