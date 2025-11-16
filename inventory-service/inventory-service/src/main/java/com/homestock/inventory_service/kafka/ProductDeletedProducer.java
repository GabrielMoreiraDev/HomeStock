package com.homestock.inventory_service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.homestock.inventory_service.dto.ProductCreatedEvent;
import com.homestock.inventory_service.dto.ProductDeletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDeletedProducer {
    private static final String TOPIC = "productDeleted";
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendProductCreate(ProductDeletedEvent event) {
        try {
            String listItemJson = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(TOPIC, listItemJson);
            System.out.println("✅ ProductDeleted sent: " + listItemJson);
        } catch (JsonProcessingException e) {
            System.err.println("❌ Error serializing product: " + e.getMessage());
        }
    }
}
