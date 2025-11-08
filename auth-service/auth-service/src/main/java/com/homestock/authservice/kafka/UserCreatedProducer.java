package com.homestock.authservice.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.homestock.authservice.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserCreatedProducer {
    private static final String TOPIC = "userCreated";
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendUserCreatedEvent(User user) {
        try {
            String userJson = objectMapper.writeValueAsString(user);
            kafkaTemplate.send(TOPIC, userJson);
            System.out.println("✅ UserCreatedEvent sent: " + userJson);
        } catch (JsonProcessingException e) {
            System.err.println("❌ Error serializing user: " + e.getMessage());
        }
    }
}
