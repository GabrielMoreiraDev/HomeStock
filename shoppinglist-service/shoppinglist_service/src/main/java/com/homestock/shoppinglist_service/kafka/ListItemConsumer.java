package com.homestock.shoppinglist_service.kafka;


import com.homestock.shoppinglist_service.dto.AddListItemEvent;
import com.homestock.shoppinglist_service.dto.ProductCreatedEvent;
import com.homestock.shoppinglist_service.dto.ProductDeletedEvent;
import com.homestock.shoppinglist_service.exception.NotFound;
import com.homestock.shoppinglist_service.model.ListItem;
import com.homestock.shoppinglist_service.model.Product;
import com.homestock.shoppinglist_service.repository.ListItemRepository;
import com.homestock.shoppinglist_service.repository.ProductRepository;
import com.homestock.shoppinglist_service.utils.ListItemUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ListItemConsumer {
    private final ListItemRepository listItemRepository;
    private final ProductRepository productRepository;
    private final ListItemUtils listItemUtils;

    @KafkaListener(
            topics = "addListItem",
            groupId = "shoppinglist-service-consumer",
            containerFactory = "addListItemKafkaListenerFactory"
    )
    public void consumeUserGroupCreated(AddListItemEvent event) {
        try {
            int position = listItemUtils.findNextPosition(event.getProductId());

            ListItem listItem = ListItem.builder()
                    .productId(event.getProductId())
                    .quantity(event.getQuantity())
                    .position(position)
                    .build();

            listItemRepository.save(listItem);

            System.out.println("✅ List Item saved: " + listItem.getProductId());
        } catch (Exception e) {
            System.err.println("❌ Failed to process message: " + e.getMessage());
        }
    }

    @KafkaListener(
            topics = "productCreated",
            groupId = "shoppinglist-service-consumer",
            containerFactory = "productCreatedKafkaListenerFactory"
    )
    public void consumeProductCreated(ProductCreatedEvent event) {
        try {
            Product product = Product.builder()
                    .id(event.getId())
                    .groupId(event.getGroupId())
                    .build();

            productRepository.save(product);

            System.out.println("✅ Product saved: " + product.getId());
        } catch (Exception e) {
            System.err.println("❌ Failed to process message: " + e.getMessage());
        }
    }

    @KafkaListener(
            topics = "productDeleted",
            groupId = "shoppinglist-service-consumer",
            containerFactory = "productDeletedKafkaListenerFactory"
    )
    public void consumeProductDeleted(ProductDeletedEvent event) {
        try {
            Product product = productRepository.findById(event.getProductId()).orElseThrow(
                    () -> new NotFound("Product not found!")
            );

            productRepository.delete(product);

            System.out.println("✅ Product deleted: " + product.getId());
        } catch (Exception e) {
            System.err.println("❌ Failed to process message: " + e.getMessage());
        }
    }
}
