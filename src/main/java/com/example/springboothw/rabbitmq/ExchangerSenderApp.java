package com.example.springboothw.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import static com.example.springboothw.SpringBootHwApplication.*;

/*
Цепочка связей для передачи сообещния
1. Producer

2. Exchanger: занимается рассылкой сообщений
Direct - отправляет в конкретную очередь
Topic - использует routing key, по которым определяется очередь
Fanout - отправляет сообщения всем очередям,  к которым он привязан

3. Binding - задает привязку Exchanger с Queues
4. Queues
5. Consumer

*/
@RestController
public class ExchangerSenderApp {
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void sendMessage(String id_order) {

        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGER_NAME_SENDER, "confirmation", id_order);
    }

}
