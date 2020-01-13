package com.example.springboothw.services.impl;

import com.example.springboothw.entities.Product;
import com.example.springboothw.entities.Review;
import com.example.springboothw.entities.User;
import com.example.springboothw.repositories.ReviewRepository;
import com.example.springboothw.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;

    @Autowired
    public void setReviewRepository(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review findByUser(User user) {
        return reviewRepository.findByUser(user);
    }

    public Review findByUserAndProduct(User user, Product product){
       return reviewRepository.findByUserAndProduct(user,product);
    }

    @Override
    public void save(Review review) {
        reviewRepository.save(review);
    };
}
