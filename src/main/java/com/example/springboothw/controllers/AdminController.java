package com.example.springboothw.controllers;

import com.example.springboothw.entities.User;
import com.example.springboothw.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path="/showListUsers")
    public String getAllUser(Model model){
        List<User> users= (List<User>) userService.findAll();
        model.addAttribute("users",users);
        return "user_list";
    }
}
