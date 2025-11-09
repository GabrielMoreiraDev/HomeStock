package com.homestock.inventory_service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.homestock.inventory_service.dto.ProductReachedThresholdEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductReachedThresholdProducer {
    private static final String TOPIC = "productReachedThreshold";
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendProductReachedThreshold(ProductReachedThresholdEvent event) {
        try {
            String listItemJson = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(TOPIC, listItemJson);
            System.out.println("✅ ProductReachedThresholdEvent sent: " + listItemJson);
        } catch (JsonProcessingException e) {
            System.err.println("❌ Error serializing user: " + e.getMessage());
        }
    }
}
