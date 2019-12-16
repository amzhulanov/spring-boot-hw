package com.example.springboothw.utils;

import com.example.springboothw.entities.Product;
import com.example.springboothw.utils.spec.ProductSpecifications;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

@Getter
public class ProductFilter {
    private Specification<Product> spec;
    private StringBuilder filterDefinition;

    public ProductFilter(Map<String, String> map) {
        this.spec = Specification.where(null);
        this.filterDefinition = new StringBuilder();
        if (map.containsKey("minPrice") && !map.get("minPrice").isEmpty()) {
            int minPrice = Integer.parseInt(map.get("minPrice"));
            spec = spec.and(ProductSpecifications.priceGEThan(minPrice));
            filterDefinition.append("&minPrice=").append(minPrice);
        }
        if (map.containsKey("maxPrice") && !map.get("maxPrice").isEmpty()) {
            int maxPrice = Integer.parseInt(map.get("maxPrice"));
            spec = spec.and(ProductSpecifications.priceLEThan(maxPrice));
            filterDefinition.append("&maxPrice=").append(maxPrice);
        }
        if (map.containsKey("category")){
            String category=map.get("category");
            spec=spec.and(ProductSpecifications.categoryContains(category));
            filterDefinition.append("&category=").append(category);
        }
    }
}
