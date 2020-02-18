package com.example.springboothw;

import com.example.springboothw.rabbitmq.Receiver;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

//ДЗ
//При оформлении заказа в очередь отправляется сообщение об этом
//есть консольное приложение, которое должно получать сообщение о новом заказе
//когда пользователь введет команду Ок в консольном приложении, статус заказа должен быть изменен
@SpringBootApplication
public class SpringBootHwApplication {
    public static final String TOPIC_EXCHANGER_NAME_SENDER = "ExchangerSender";
    private static final String TOPIC_EXCHANGER_NAME_RECIEVER = "ExchangerReciever";
    private static final String QUEUE_NAME_SENDER = "OrderQueueSender";
    private static final String QUEUE_NAME_RECIEVER = "OrderQueueReciever";
    private static final String ROUTING_KEY = "confirmation";

    //объявляется Exchanger
    @Bean
    TopicExchange topicExchangeSender() {
        return new TopicExchange(TOPIC_EXCHANGER_NAME_SENDER);
    }

    @Bean
    TopicExchange topicExchangeReciever() {
        return new TopicExchange(TOPIC_EXCHANGER_NAME_RECIEVER);
    }

    //объявляется очередь
    @Bean
    Queue queueTopicSender() {
        return new Queue(QUEUE_NAME_SENDER, false, false, true);
    }

    @Bean
    Queue queueTopicReciever() {
        return new Queue(QUEUE_NAME_RECIEVER, false, false, true);
    }

    //устанавливается связь между Exchanger и Queue (binding)
    //подписываю на определённый ключ
    @Bean
    Binding bindingTopicSender(@Qualifier("queueTopicSender") Queue queue, TopicExchange topicExchangeSender) {
        return BindingBuilder.bind(queue).to(topicExchangeSender).with(ROUTING_KEY);
    }

    @Bean
    Binding bindingTopicReciever(@Qualifier("queueTopicReciever") Queue queue, TopicExchange topicExchangeReciever) {
        return BindingBuilder.bind(queue).to(topicExchangeReciever).with(ROUTING_KEY);
    }

    @Bean
    SimpleMessageListenerContainer containerForTopic(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME_RECIEVER);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootHwApplication.class, args);

    }

}



