package com.example.springboothw.services;

import com.example.springboothw.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByPhone(String phone);
    boolean isUserExist(String phone);
    Iterable<User> findAll();
    void save(User user);
    User saveUser(User user);
    User findOneByEmail(String email);
    User findUserByUrl(String url);
    void updateStatusUserById(Long id, String status);
}