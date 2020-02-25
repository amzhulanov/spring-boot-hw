package com.example.springboothw.services;

import com.example.springboothw.entities.Product;
import com.example.springboothw.entities.Review;
import com.example.springboothw.entities.User;

import java.util.List;

public interface ReviewService {
    Review findByUser(User user);
    Review findByUserAndProduct(User user, Product product);
    void save(Product product,String review_description, User user,Integer rating);
}
