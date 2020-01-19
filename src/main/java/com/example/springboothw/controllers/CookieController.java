package com.example.springboothw.controllers;

import com.example.springboothw.entities.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CookieController {

//    @GetMapping("/readCookie")
//    public ModelAndView readCookie(@CookieValue(value = "cookieName", required = false) Cookie cookieName, HttpServletRequest request) {
//        String cookieValue = "cookie with name 'cookieName' is empty";
//        if (cookieName != null) {
//            cookieValue  = "Object: " + cookieName + ";<br/> Name: " + cookieName.getName() + ";<br/> Value: " + cookieName.getValue();
//        }
//        return new ModelAndView("/cookie/cookieView", "cookieValueObj", cookieValue);
//    }


    public void writeCookie(HttpServletResponse response, Product product) {
        System.out.println("CookieControllerExample writeCookieExample() is called");
        Cookie cookie = new Cookie("productId", product.getId().toString());
        response.addCookie(cookie);

    }


    public List<Cookie> readAllCookies(HttpServletRequest request) {
        System.out.println("CookieControllerExample readAllCookiesExample() is called");
        List<Cookie> cookies =new ArrayList<>();// = Arrays.asList(request.getCookies());
        for (Cookie c:request.getCookies()){
            if (c.getName().equals("productId")){
                cookies.add(c);
            }
        }
        return cookies;
    }
}
