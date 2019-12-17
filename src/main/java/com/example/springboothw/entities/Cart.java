package com.example.springboothw.entities;

import com.example.springboothw.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

@Component("cart")
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {
    private ProductService productService;
    private List<Product> products;

    public Cart() {
        products=new ArrayList<>();
    }

    @Autowired
    public void setProductService(ProductService productService){
        this.productService=productService;
    }

    public void addProductToCart(Long prod_id){
        products.add(productService.findById(prod_id));
        System.out.println("в корзину добавлен продукт с id="+prod_id);
        System.out.println(products.get(0).getTitle());
    }

    public void removeProductFromCart(Long prod_id){


        for (Product p:products) {
            for (int i=0;i<products.size();i++){
                if (products.get(i).getId()==prod_id){
                    products.remove(i);
                    return;
                }

            }


        }
    }

    public Integer countProdCart(){
        return products.size();
    }

    public List<Product> getAll(){
        return products;
    }



}
