package com.homestock.group_service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.homestock.group_service.dto.UserGroupUpdatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserGroupUpdatedProducer {
    private static final String TOPIC = "userGroupUpdated";
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendUserGroupUpdateEvent(UserGroupUpdatedEvent event) {
        try {
            String userGroupJson = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(TOPIC, userGroupJson);
            System.out.println("✅ UserGroupUpdatedEvent sent: " + userGroupJson);
        } catch (JsonProcessingException e) {
            System.err.println("❌ Error serializing user: " + e.getMessage());
        }
    }
}
