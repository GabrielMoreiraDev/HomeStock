package com.homestock.group_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic userCreatedTopic() {
        return TopicBuilder.name("userCreated")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic userGroupCreatedTopic() {
        return TopicBuilder.name("userGroupCreated")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic userGroupDeletedTopic() {
        return TopicBuilder.name("userGroupDeleted")
                .partitions(1)
                .replicas(1)
                .build();
    }
}

