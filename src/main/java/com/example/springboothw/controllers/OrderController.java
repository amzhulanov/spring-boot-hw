package com.example.springboothw.controllers;

import com.example.springboothw.entities.Address;
import com.example.springboothw.entities.Order;
import com.example.springboothw.entities.OrderItem;
import com.example.springboothw.entities.User;
import com.example.springboothw.services.OrderService;
import com.example.springboothw.services.UserService;
import com.example.springboothw.utils.Cart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private UserService userService;
    private OrderService orderService;
    private Cart cart;

    public OrderController(UserService userService, Cart cart, OrderService orderService) {
        this.userService = userService;
        this.cart = cart;
        this.orderService = orderService;
    }

    @GetMapping
    public String showOrders(Principal principal,Model model){
        User user = userService.findByPhone(principal.getName());
        List<Order> orders= orderService.findAllOrdersByUser(user);

       // List<OrderItem> orderItems=orderService.findAllOrderItemsByOrders(orders);// orders.get(0).getItems();

        model.addAttribute("orders",orders);
        model.addAttribute("cost", orderService.costOrders(orders));
        return "orders_history";
    }

    @GetMapping("/open/{id}")
    public String openOrder(Model model,@PathVariable Long id){
        Optional<Order> order= orderService.findById(id);
        model.addAttribute("order",order);
        return "order_form";
    }

    @GetMapping("/create")
    public String createOrder(Principal principal, Model model) {
        User user = userService.findByPhone(principal.getName());
        model.addAttribute(cart);
        model.addAttribute("user",user);
        return "save_order";
    }

    @PostMapping("/commit")
    public String commitOrder(Principal principal, Model model, @RequestParam Map<String, String> params) {
        Address address = new Address();
        address.setCity(params.get("city"));
        address.setStreet(params.get("street"));
        address.setHouse(params.get("house"));

        System.out.println("Пользователь = "+principal.getName());
        User user = userService.findByPhone(principal.getName());
        Order order = new Order(user, cart, address);
        orderService.save(order);
        model.addAttribute("order_id_str", String.format("%05d", order.getId()));
        return "order_confirmation";
    }




}
