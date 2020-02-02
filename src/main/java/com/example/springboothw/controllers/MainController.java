package com.example.springboothw.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/cookie")
    @ResponseBody
    public String cookie(HttpServletRequest request, HttpServletResponse response){
        for (Cookie c:request.getCookies()){
            if (c.getName().equals("data")){
                return c.getValue();
            }
        }
        response.addCookie(new Cookie("data","hello"));
        return "cookie data is empty. cookie added";
    }

    @GetMapping("/cookie/reset")
    @ResponseBody
    public String resetCookie(HttpServletRequest request, HttpServletResponse response){
        for (Cookie c:request.getCookies()){

            if (c.getName().equals("data")){
                c.setMaxAge(0);
                response.addCookie(c);
                return "reset ok";
            }
        }
        return "oops";
    }

}
