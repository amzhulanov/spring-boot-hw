package com.example.springboothw.services;

import com.example.springboothw.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.swing.text.StyledEditorKit;
import java.util.Optional;

public interface UserService extends UserDetailsService {


    //User findByPhone(String phone,Boolean buyOneClick);
    User findByPhone(String phone);
    boolean isUserExist(String phone);
    Iterable<User> findAll();

    void save(User user);
    User saveUser(User user);
   // User addNewUser(String phone);
}