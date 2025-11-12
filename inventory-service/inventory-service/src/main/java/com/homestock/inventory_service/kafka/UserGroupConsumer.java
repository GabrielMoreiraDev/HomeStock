package com.homestock.inventory_service.kafka;

import com.homestock.inventory_service.dto.UserGroupCreatedEvent;
import com.homestock.inventory_service.dto.UserGroupDeletedEvent;
import com.homestock.inventory_service.dto.UserGroupUpdatedEvent;
import com.homestock.inventory_service.exception.NotFound;
import com.homestock.inventory_service.model.UserGroup;
import com.homestock.inventory_service.repository.UserGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserGroupConsumer {
    private final UserGroupRepository userGroupRepository;

    @KafkaListener(
            topics = "userGroupCreated",
            groupId = "inventory-service-consumer",
            containerFactory = "userGroupCreatedKafkaListenerFactory"
    )
    public void consumeUserGroupCreated(UserGroupCreatedEvent event) {
        try {
            UserGroup userGroup = UserGroup.builder()
                    .id(event.getId())
                    .role(event.getRole())
                    .userId(event.getUserId())
                    .groupId(event.getGroupId())
                    .build();
            userGroupRepository.save(userGroup);

            System.out.println("✅ UserGroup saved: " + userGroup.getRole());
        } catch (Exception e) {
            System.err.println("❌ Failed to process message: " + e.getMessage());
        }
    }

    @KafkaListener(
            topics = "userGroupDeleted",
            groupId = "inventory-service-consumer",
            containerFactory = "userGroupDeletedKafkaListenerFactory"
    )
    public void consumeUserGroupDeleted(UserGroupDeletedEvent event) {
        try {

            UserGroup userGroup = userGroupRepository.findById(event.getId()).orElseThrow(
                    () -> new NotFound("Relation not found!")
            );

            userGroupRepository.delete(userGroup);

            System.out.println("✅ UserGroup deleted: " + userGroup.getRole());
        } catch (Exception e) {
            System.err.println("❌ Failed to process message: " + e.getMessage());
        }
    }

    @KafkaListener(
            topics = "userGroupUpdated",
            groupId = "inventory-service-consumer",
            containerFactory = "userGroupUpdatedKafkaListenerFactory"
    )
    public void consumeUserGroupUpdated(UserGroupUpdatedEvent event) {
        try {

            UserGroup userGroup = userGroupRepository.findById(event.getId()).orElseThrow(
                    () -> new NotFound("Relation not found!")
            );

            userGroup.setRole(event.getRole());
            userGroupRepository.save(userGroup);

            UserGroup userGroup2 = userGroupRepository.findById(event.getId()).orElseThrow(
                    () -> new NotFound("Relation not found!")
            );

            System.out.println("✅ UserGroup updated: " + userGroup2.getRole());
        } catch (Exception e) {
            System.err.println("❌ Failed to process message: " + e.getMessage());
        }
    }
}

