package com.store.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ProductConsumer {

    @KafkaListener(topics = "user-topic", groupId = "product_group")
    public void consumeMessage(String message) {
        System.out.println("Received message: " + message);
        // Логіка обробки повідомлення
    }
}