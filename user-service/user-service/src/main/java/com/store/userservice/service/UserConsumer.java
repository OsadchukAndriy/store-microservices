package com.store.userservice.service;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserConsumer {

    @KafkaListener(topics = "order_topic", groupId = "user_group")
    public void listenOrderEvents(String message) {
        System.out.println("Received message from OrderService: " + message);
        // Додамо логіку обробки повідомлення
    }
}
