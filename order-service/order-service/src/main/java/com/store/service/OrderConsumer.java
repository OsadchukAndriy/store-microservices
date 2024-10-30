package com.store.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    @KafkaListener(topics = "product_topic", groupId = "order_group")
    public void listenProductEvents(String message) {
        System.out.println("Received message from ProductService: " + message);
        // Додамо логіку обробки повідомлення
    }
}