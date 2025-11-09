package com.homestock.group_service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.homestock.group_service.dto.UserGroupCreateEvent;
import com.homestock.group_service.model.UserGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserGroupCreatedProducer {
    private static final String TOPIC = "userGroupCreated";
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendUserGroupCreatedEvent(UserGroup userGroup) {
        try {
            UserGroupCreateEvent userGroupCreateEvent = UserGroupCreateEvent.builder()
                    .role(userGroup.getRole())
                    .id(userGroup.getId())
                    .userId(userGroup.getUser().getId())
                    .groupId(userGroup.getGroup().getId())
                    .build();

            String userGroupJson = objectMapper.writeValueAsString(userGroupCreateEvent);
            kafkaTemplate.send(TOPIC, userGroupJson);
            System.out.println("✅ UserGroupCreatedEvent sent: " + userGroupJson);
        } catch (JsonProcessingException e) {
            System.err.println("❌ Error serializing user: " + e.getMessage());
        }
    }
}
