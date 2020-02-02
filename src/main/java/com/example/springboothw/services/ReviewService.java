package com.example.springboothw.services;

import com.example.springboothw.entities.Product;
import com.example.springboothw.entities.Review;
import com.example.springboothw.entities.User;

public interface ReviewService {
    Review findByUser(User user);
    Review findByUserAndProduct(User user, Product product);
    void save(Review review);
}
