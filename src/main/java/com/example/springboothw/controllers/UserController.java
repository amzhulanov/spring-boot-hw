package com.example.springboothw.controllers;

import com.example.springboothw.entities.User;
import com.example.springboothw.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;


    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/login")
    public String loginPage() {
        return "login_page";
    }

    @GetMapping("/profile")
    public String profilePage(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }
        User user = userService.findByPhone(principal.getName());
        System.out.println("телефон - "+user.getPhone()+" : пароль - "+user.getPassword());
        model.addAttribute("user", user);
        System.out.println("возвращаю данные юзера в профиль");
        return "profile";
    }
}
