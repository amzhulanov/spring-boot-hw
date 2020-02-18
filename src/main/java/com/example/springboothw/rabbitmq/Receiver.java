package com.example.springboothw.rabbitmq;

import com.example.springboothw.services.OrderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Receiver {
    private RabbitTemplate rabbitTemplate;
    private OrderService orderService;

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate,OrderService orderService) {
        this.rabbitTemplate = rabbitTemplate;
        this.orderService=orderService;
    }

    public void receiveMessage(String message) {
        System.out.println("Confirmed oder №"+ message);
        orderService.orderConfirmed(Long.parseLong(message));
    }
}
