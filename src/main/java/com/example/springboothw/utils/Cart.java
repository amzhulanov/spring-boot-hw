package com.example.springboothw.utils;

import com.example.springboothw.entities.OrderItem;
import com.example.springboothw.entities.Product;
import com.example.springboothw.services.ProductService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component("cart")
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class Cart {

    private BigDecimal cost;
 //   private ProductService productService;
    private List<OrderItem> items;

    @PostConstruct
    public void init(){items=new ArrayList<>();}

    public void clear() {
        items.clear();
        recalculate();
    }

public void add(Product product) {
    for (OrderItem i : items) {
        if (i.getProduct().getId().equals(product.getId())) {
            i.setQuantity(i.getQuantity() + 1);
            i.setCost(new BigDecimal(i.getQuantity() * i.getProduct().getCost().doubleValue()));
            recalculate();

            return;
        }
    }
    items.add(new OrderItem(product));
    recalculate();
}

    public void removeById(Long productId) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProduct().getId().equals(productId)) {
                items.remove(i);
                recalculate();
                return;
            }
        }
    }

    public Integer countProdCart(){
        return items.size();
    }

    public void recalculate() {
        cost = new BigDecimal(0.0);
        for (OrderItem i : items) {
            cost = cost.add(i.getCost());
        }
    }

}
