package com.homestock.shoppinglist_service.config;

import com.homestock.shoppinglist_service.dto.UserGroupCreatedEvent;
import com.homestock.shoppinglist_service.dto.UserGroupDeletedEvent;
import com.homestock.shoppinglist_service.dto.UserGroupUpdatedEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    private Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "shoppinglist-service-consumer");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }

    // ---- Factory para UserGroupCreatedEvent ----
    @Bean
    public ConsumerFactory<String, UserGroupCreatedEvent> userGroupCreatedConsumerFactory() {
        JsonDeserializer<UserGroupCreatedEvent> deserializer =
                new JsonDeserializer<>(UserGroupCreatedEvent.class);
        deserializer.addTrustedPackages("*");

        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, UserGroupCreatedEvent> userGroupCreatedKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, UserGroupCreatedEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(userGroupCreatedConsumerFactory());
        return factory;
    }

    // ---- Factory para UserGroupDeletedEvent ----
    @Bean
    public ConsumerFactory<String, UserGroupDeletedEvent> userGroupDeletedConsumerFactory() {
        JsonDeserializer<UserGroupDeletedEvent> deserializer =
                new JsonDeserializer<>(UserGroupDeletedEvent.class);
        deserializer.addTrustedPackages("*");

        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, UserGroupDeletedEvent> userGroupDeletedKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, UserGroupDeletedEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(userGroupDeletedConsumerFactory());
        return factory;
    }

    // ---- Factory para UserGroupUpdatedEvent ----
    @Bean
    public ConsumerFactory<String, UserGroupUpdatedEvent> userGroupUpdatedConsumerFactory() {
        JsonDeserializer<UserGroupUpdatedEvent> deserializer =
                new JsonDeserializer<>(UserGroupUpdatedEvent.class);
        deserializer.addTrustedPackages("*");

        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, UserGroupUpdatedEvent> userGroupUpdatedKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, UserGroupUpdatedEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(userGroupUpdatedConsumerFactory());
        return factory;
    }
}
