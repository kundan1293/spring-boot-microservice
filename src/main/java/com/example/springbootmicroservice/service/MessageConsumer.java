package com.example.springbootmicroservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {
    @KafkaListener(topics = "user-events", groupId = "my-group")
    public void listen(String message) {
        System.out.println("Received message: " + message);
        // Process the message
    }
}