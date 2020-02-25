package com.example.springboothw.services;

import com.example.springboothw.entities.Order;
import com.example.springboothw.entities.User;

public interface MailService {
    void sendOrderMail(Order order);
    void sendRegConfirmation(User user);
}