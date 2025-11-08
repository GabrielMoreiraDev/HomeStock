package com.homestock.group_service.kafka;

import com.homestock.group_service.dto.UserCreatedEvent;
import com.homestock.group_service.model.User;
import com.homestock.group_service.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCreatedConsumer {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "userCreated", groupId = "group-service-consumer")
    public void consume(UserCreatedEvent event) {
        try {
            User user = User.builder()
                    .id(event.getId())
                    .email(event.getEmail())
                    .name(event.getName())
                    .build();
            userRepository.save(user);

            System.out.println("✅ User saved: " + user.getEmail());
        } catch (Exception e) {
            System.err.println("❌ Failed to process message: " + e.getMessage());
        }
    }
}
