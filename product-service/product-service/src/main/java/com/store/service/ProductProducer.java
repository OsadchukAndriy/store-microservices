package com.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductProducer {

    private static final String TOPIC = "product_topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendProductCreatedEvent(String message) {
        kafkaTemplate.send(TOPIC, message);
    }
}
