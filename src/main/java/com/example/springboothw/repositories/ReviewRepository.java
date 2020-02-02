package com.example.springboothw.repositories;

import com.example.springboothw.entities.Product;
import com.example.springboothw.entities.Review;
import com.example.springboothw.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    Review findByUser(User user);

    @Query("from Review r where r.user=:user and r.product=:product")
    Review findByUserAndProduct(User user, Product product);



}
