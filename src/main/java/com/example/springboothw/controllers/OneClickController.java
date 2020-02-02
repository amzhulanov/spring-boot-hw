package com.example.springboothw.controllers;

import com.example.springboothw.entities.Order;
import com.example.springboothw.entities.User;
import com.example.springboothw.services.OrderService;
import com.example.springboothw.services.UserService;
import com.example.springboothw.utils.Cart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Map;


@Controller
@RequestMapping("/oneclick")
public class OneClickController {
    private UserService userService;
    private OrderService orderService;
    private Cart cart;

    public OneClickController(UserService userService, Cart cart, OrderService orderService) {
        this.userService = userService;
        this.cart = cart;
        this.orderService = orderService;
    }


    @PostMapping("/create")
    public String buyOneClick(Model model){
        model.addAttribute(cart);
        return "buy_one_click";
    }

    /**
     * При покупке в один клик в базе создаётся пользователь с указанным номером телефона и ролью ROLE_ONECLICK,
     * Если в базе уже существует такой пользователь, то используется id этого пользователя
     *
     */
    @PostMapping("/commit")
    public String commitBuyOneClick(Principal principal, Model model, @RequestParam Map<String, String> params) {
        User user = userService.addNewUser(params.get("phone"));
        Order order = new Order(user, cart);
        orderService.save(order);
        model.addAttribute("order_id_str", String.format("%05d", order.getId()));
        return "order_confirmation";
    }
}
