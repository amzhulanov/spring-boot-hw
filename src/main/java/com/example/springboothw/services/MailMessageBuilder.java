package com.example.springboothw.services;

import com.example.springboothw.entities.Order;
import com.example.springboothw.entities.User;

public interface MailMessageBuilder {
    String buildRegConfirmationEmail(User user, String url);
    String buildOrderEmail(Order order);
}
